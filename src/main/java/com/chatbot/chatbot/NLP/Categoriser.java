package com.chatbot.chatbot.NLP;

import net.sf.hfst.NoTokenizationException;
import opennlp.tools.doccat.*;
import opennlp.tools.ml.AbstractTrainer;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Categoriser {
    public static void categoriser(String sentence){
        try {
            // read the training data
            InputStreamFactory dataIn = new MarkableFileInputStreamFactory(new File("/Users/yassine/IdeaProjects/gestionDeTaches/ChatBot/src/main/java/com/chatbot/chatbot/categories.txt"));
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);

            // define the training parameters
            TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.ITERATIONS_PARAM, 10+"");
            params.put(TrainingParameters.CUTOFF_PARAM, 0+"");
            params.put(AbstractTrainer.ALGORITHM_PARAM, NaiveBayesTrainer.NAIVE_BAYES_VALUE);

            // create a model from traning data
            DoccatModel model = DocumentCategorizerME.train("fr", sampleStream, params, new DoccatFactory());
            System.out.println("\nModel is successfully trained.");

            // save the model to local
            BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("en-movie-classifier-naive-bayes.bin"));
            model.serialize(modelOut);
            System.out.println("\nTrained Model is saved locally at : "+"model"+File.separator+"en-movie-classifier-naive-bayes.bin");

            // test the model file by subjecting it to prediction
            DocumentCategorizer doccat = new DocumentCategorizerME(model);
            String [] lemmas =Lemmatizer.getLemmas(sentence);
            double[] aProbs = doccat.categorize(lemmas);

            // print the probabilities of the categories
            System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
            for(int i=0;i<doccat.getNumberOfCategories();i++){
                System.out.println(doccat.getCategory(i)+" : "+aProbs[i]);
            }
            System.out.println("---------------------------------");

            System.out.println("\n"+doccat.getBestCategory(aProbs)+" : is the predicted category for the given sentence.");
        }
        catch (IOException | NoTokenizationException e) {
            System.out.println("An exception in reading the training file. Please check.");
            e.printStackTrace();
        }
    }
}
