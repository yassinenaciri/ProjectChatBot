package com.chatbot.chatbot.NLP;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DetecteurPhrases {
    public static String [] getPhrases(String message) throws IOException {
        //Loading sentence detector model
        InputStream inputStream = new FileInputStream("/Users/yassine/Downloads/AllFiveLanguages/setenceDetectionModels/fr-sent.bin");
        SentenceModel model = new SentenceModel(inputStream);

        //Instantiating the SentenceDetectorME class
        SentenceDetectorME detector = new SentenceDetectorME(model);

        //Detecting the sentence
        String sentences[] = detector.sentDetect(message);

        //Printing the sentences
        return sentences;
    }
}
