package ctci;

import java.util.Arrays;

/**
 * Created by chungnguyen on 6/28/17.
 */
public class SentimentAnalyzer {

    // Tip: labeled continue can be used when iterating featureSet + convert review to lower-case
    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords,
                                          String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length]; // output

        // your code ~ you will be invoking getOpinionOnFeature
        review = review.toLowerCase();

        // get the FEATURE
        banana:
        for (int i = 0; i < featureSet.length; i++) {
            for (String feature : featureSet[i]) {
                if (review.indexOf(feature) > 0) {
                    int opinion = getOpinionOnFeature(review, feature, posOpinionWords, negOpinionWords);
                    System.out.println("This is the opinion: " + opinion + "\n" + "Here's the i: " + i + " and feature: " + feature);
                    featureOpinions[i] = opinion;
                    continue banana;
                }
            }
        }

        return featureOpinions;
    }

    // First invoke checkForWasPhrasePattern and
    // if it cannot find an opinion only then invoke checkForOpinionFirstPattern
    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {

        int opinion = 0;

        opinion = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        System.out.println("Inside get opinion with feature and opinion: " + feature + " " + opinion);

        if (opinion == 0) {
            opinion = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
            System.out.println("Inside get opinion with feature and opinion: " + feature + " " + opinion);
        }

        return opinion;
    }

    // Tip: Look at String API doc. Methods like indexOf, length, substring(beginIndex), startsWith can come into play
    // Return 1 if positive opinion found, -1 for negative opinion, 0 for no opinion
    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        if (review.length() == 0) {
            return 0;
        }

        int opinion = 0;
        String pattern = feature + " was ";

        if (review.indexOf(pattern) > 0) {
            int opinionLocation = pattern.length() + review.indexOf(pattern);

            String reviewOpinion = review.substring(opinionLocation);

            for (String positiveOpinion : posOpinionWords) {
                if (reviewOpinion.startsWith(positiveOpinion)) return 1;
            }

            for (String negativeOpinion : negOpinionWords) {
                if (reviewOpinion.startsWith(negativeOpinion)) return -1;
            }
        } else {
            return 0;
        }

        return opinion;
    }

    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        // Extract sentences as feature might appear multiple times.
        // split() takes a regular expression and "." is a special character
        // for regular expression. So, escape it to make it work!!
        String[] sentences = review.split("\\.");
        int opinion = 0;

        // your code for processing each sentence. You can return if opinion is found in a sentence (no need to process subsequent ones)
        for (String sentence : sentences) {
            for (String posWord : posOpinionWords) {
                if (sentence.indexOf(posWord) > 0) return 1;
            }
            for (String negWord : negOpinionWords) {
                if (sentence.indexOf(negWord) > 0) return -1;
            }
        }

        return opinion;
    }

    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";

        //String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";

        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                { "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
        String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
                "delicious" };
        String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}

