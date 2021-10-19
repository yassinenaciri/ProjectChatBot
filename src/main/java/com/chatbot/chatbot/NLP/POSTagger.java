package com.chatbot.chatbot.NLP;

import net.sf.hfst.NoTokenizationException;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class POSTagger {
    public static String [] getTags(String[] tokens) throws IOException, NoTokenizationException {
        InputStream inputStream = new
                FileInputStream("/Users/yassine/Downloads/AllFiveLanguages/posModels/fr-pos-maxent.bin");
        POSModel model = new POSModel(inputStream);

        //Instantiating POSTaggerME class
        POSTaggerME tagger = new POSTaggerME(model);


        //Generating tags
        String[] tags = tagger.tag(tokens);

        //Instantiating the POSSample class
        POSSample sample = new POSSample(tokens, tags);
        System.out.println(sample.toString());
        return sample.getTags() ;
    }
}
