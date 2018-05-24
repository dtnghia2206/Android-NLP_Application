package com.dtnghia.nlp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
public class MainActivity extends AppCompatActivity {
    String str = "";
    String tag = "debug";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        String text = "This is Stanford CoreNLP example";
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        Log.d(tag,"Here");
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lem = token.get(CoreAnnotations.LemmaAnnotation.class);
                str = (String.format("Print: word: [%s] pos: [%s] lemma: [%s]", word, pos, lem));
            }
        }
        TextView txt = (TextView) findViewById(R.id.text1);
        txt.setText(txt.getText()+str+"\n");
    }
}