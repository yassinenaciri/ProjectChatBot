package com.chatbot.chatbot;
import java.io.*;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Tokenizer {

    public static String [] getTokens(String sentence) throws IOException {


        //Loading the Tokenizer model
        InputStream inputStream = new FileInputStream("/Users/yassine/Downloads/AllFiveLanguages/tokenizerModels/fr-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(inputStream);

        //Instantiating the TokenizerME class
        TokenizerME tokenizer = new TokenizerME(tokenModel);

        //Tokenizing the given raw text
        String tokens[] = tokenizer.tokenize(sentence);
        System.out.println("tokens");
        String lemmas[] ;
        for (String a : tokens){
            System.out.println(a);
        }
        System.out.println("endTokens");
        return tokens;
    }
        }
