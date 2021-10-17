package com.chatbot.chatbot;
import java.io.*;

import opennlp.tools.doccat.*;
import opennlp.tools.ml.AbstractTrainer;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.*;

public class Tokenizer {

        public void partsOfSpeech(String sentence) throws IOException {
            InputStream inputStream = new
                    FileInputStream("/Users/yassine/Desktop/english/opennlp-en-ud-ewt-pos-1.0-1.9.3.bin");
            POSModel model = new POSModel(inputStream);

            //Instantiating POSTaggerME class
            POSTaggerME tagger = new POSTaggerME(model);



            //Tokenizing the sentence using WhitespaceTokenizer class
            WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
            String[] tokens = whitespaceTokenizer.tokenize(sentence);

            //Generating tags
            String[] tags = tagger.tag(getTokens(sentence));

            //Instantiating the POSSample class
            POSSample sample = new POSSample(tokens, tags);
            System.out.println(sample.toString());
        }

        public String [] getTokens(String phrase){
            WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
            String[] tokens = whitespaceTokenizer.tokenize(phrase);
            return tokens;
        }

        public String [] getPhrases(String message ) throws IOException {
            //Loading sentence detector model
            InputStream inputStream = new FileInputStream("/Users/yassine/Desktop/english/opennlp-en-ud-ewt-sentence-1.0-1.9.3.bin");
            SentenceModel model = new SentenceModel(inputStream);

            //Instantiating the SentenceDetectorME class
            SentenceDetectorME detector = new SentenceDetectorME(model);

            //Detecting the sentence
            String sentences[] = detector.sentDetect(message);

            //Printing the sentences
            return sentences;

        }

        public void categoriser(){
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
                DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, new DoccatFactory());
                System.out.println("\nModel is successfully trained.");

                // save the model to local
                BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("en-movie-classifier-naive-bayes.bin"));
                model.serialize(modelOut);
                System.out.println("\nTrained Model is saved locally at : "+"model"+File.separator+"en-movie-classifier-naive-bayes.bin");

                // test the model file by subjecting it to prediction
                DocumentCategorizer doccat = new DocumentCategorizerME(model);
                String docWords = "plan appointement for today";
                double[] aProbs = doccat.categorize(getTokens( docWords));

                // print the probabilities of the categories
                System.out.println("\n---------------------------------\nCategory : Probability\n---------------------------------");
                for(int i=0;i<doccat.getNumberOfCategories();i++){
                    System.out.println(doccat.getCategory(i)+" : "+aProbs[i]);
                }
                System.out.println("---------------------------------");

                System.out.println("\n"+doccat.getBestCategory(aProbs)+" : is the predicted category for the given sentence.");
            }
            catch (IOException e) {
                System.out.println("An exception in reading the training file. Please check.");
                e.printStackTrace();
            }
        }
        }
