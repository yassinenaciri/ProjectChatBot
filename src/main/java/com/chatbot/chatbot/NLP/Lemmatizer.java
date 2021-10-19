package com.chatbot.chatbot.NLP;

import java.io.*;
import java.util.Collection;

import net.sf.hfst.NoTokenizationException;
import net.sf.hfst.Transducer;
import net.sf.hfst.TransducerAlphabet;
import net.sf.hfst.TransducerHeader;
import net.sf.hfst.UnweightedTransducer;
import net.sf.hfst.WeightedTransducer;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.util.Span;

/**
 *
 * @author ahmetaker
 */
public class Lemmatizer {

    static Transducer transducer = null;
    public final static long TRANSITION_TARGET_TABLE_START = 2147483648l; // 2^31 or UINT_MAX/2 rounded up
    public final static long NO_TABLE_INDEX = 4294967295l;
    public final static float INFINITE_WEIGHT = (float) 4294967295l; // this is hopefully the same as
    // static_cast<float>(UINT_MAX) in C++
    public final static int NO_SYMBOL_NUMBER = 65535; // this is USHRT_MAX

    public static enum FlagDiacriticOperator {

        P, N, R, D, C, U
    };

    public static String[] getLemmas(String sentence) throws NoTokenizationException, IOException {
        String [] tokens = Tokenizer.getTokens(sentence);
        String [] posType =POSTagger.getTags(tokens);
        InputStream inputStream = new
                FileInputStream("/Users/yassine/Downloads/AllFiveLanguages/otherModels/fr-chunk.bin");
        ChunkerModel chunkerModel = new ChunkerModel(inputStream);
        ChunkerME chunkerME = new ChunkerME(chunkerModel);
        Span result[] = chunkerME.chunkAsSpans(tokens, posType);
        System.out.println("here");
        for (Span s : result)
            System.out.println(s);
        int nombreTokens = tokens.length;
        String [] lemmas=new String[nombreTokens];
        for(int i =0 ; i<nombreTokens;i++){
            String word =tokens[i];
            System.out.println("word :"+word);
            lemmas[i]=Lemmatizer.getLemma("/Users/yassine/Downloads/AllFiveLanguages",word,"fr",posType[i]);
        }
        return lemmas;

    }

    public static String getLemma(String aResourceFolder, String aWord, String aLang, String aPOSType) throws IOException, NoTokenizationException {
        if (transducer == null) {
            FileInputStream transducerfile = null;
            transducerfile = new FileInputStream(aResourceFolder + "/lemmaModels/" + aLang + ".hfst.ol");
            TransducerHeader h = new TransducerHeader(transducerfile);
            DataInputStream charstream = new DataInputStream(transducerfile);
            TransducerAlphabet a = new TransducerAlphabet(charstream, h.getSymbolCount());
            if (h.isWeighted()) {
                transducer = new WeightedTransducer(transducerfile, h, a);
            } else {
                transducer = new UnweightedTransducer(transducerfile, h, a);
            }

        }
        Collection<String> analyses = transducer.analyze(aWord);
        for (String analysis : analyses) {
            System.out.println(analysis);

        }
        for (String analysis : analyses) {

                String grammar = "NONE";
                String grammarCheck = "NONE";
                if ("NOUN".equalsIgnoreCase(aPOSType)) {
                    grammar = "\\+commonNoun.*";
                    grammarCheck = "+commonNoun";
                } else if ("VERB".equalsIgnoreCase(aPOSType)) {
                    grammar = "\\+verb+.*";
                    grammarCheck = "+verb+";
                } else if ("ADJ".equalsIgnoreCase(aPOSType)) {
                    grammar = "\\+adjective.*";
                    grammarCheck = "+adjective";
                } else if ("ADV".equalsIgnoreCase(aPOSType)) {
                    grammar = "\\+adverb.*";
                    grammarCheck = "+adverb";
                } else if ("PRON".equalsIgnoreCase(aPOSType) || "CONJ".equalsIgnoreCase(aPOSType)) {
                    grammar = "\\+functionWord.*";
                    grammarCheck = "+functionWord";

                }
                //System.out.println(analysis);
                if (analysis.contains(grammarCheck)) {
                    String lemma = analysis.replaceAll(grammar, "");
                    if ((lemma.contains("+") && !lemma.contains("-")) && (aWord.contains("-") && !aWord.contains("+"))) {
                        lemma = lemma.replaceAll("\\+", "-");
                    }
                    if (lemma.contains("+") && !aWord.contains("+")) {
                        lemma = lemma.replaceAll("\\+", "");
                    }
                    return lemma.toLowerCase();
                }
        }
        if (analyses.isEmpty()) {
            return null;
        }
        return null;
    }

}
