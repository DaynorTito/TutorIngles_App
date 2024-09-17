package com.example.englishtutor.chooseOption;

import android.content.Context;
import android.util.Log;

import com.example.englishtutor.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChooseOptionLogic {

    private String level;
    private List<String> correctWords;
    private List<String> alternativeWords;
    private Map<String, Integer> imagePaths;
    private Context context;

    public ChooseOptionLogic(Context context, String level) {
        this.level = level;
        this.context = context;
        this.imagePaths = new HashMap<>();
        inicializeALternativeWords();
        inicializeMapPaths();
        this.correctWords = getImageNames();
    }

    public List<OptionToSelect> generateOptions (int num) {
        List<OptionToSelect> listOptions = new ArrayList<>();
        Map<String, Integer> options = getRandomizedMap();
        List<Map.Entry<String, Integer>> mapEntries = new ArrayList<>(options.entrySet());
        int mapSize = Math.min(mapEntries.size(), num);
        for (int i = 0; i < mapSize; i++) {
            List<String> options3 = getTreeOptions();
            Map.Entry<String, Integer> entry = mapEntries.get(i);
            String valueKey = entry.getKey();
            options3.add(valueKey);
            listOptions.add(new OptionToSelect(entry.getValue(), valueKey ,options3));
        }
        return  listOptions;
    }

    public void mostrarLista(List<String> lista) {
        System.out.println("Esta es un alista");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i));
            System.out.println(" ");
        }
        System.out.println();
    }
    public List<String> getTreeOptions() {
        Collections.shuffle(alternativeWords);
        List<String> lista = new ArrayList<>();
        int limit = Math.min(alternativeWords.size(), 3);
        for (int i = 0; i < limit; i++) {
            lista.add(alternativeWords.get(i));
        }
        return lista;
    }

    public Map<String, Integer> getRandomizedMap() {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(imagePaths.entrySet());
        Collections.shuffle(entryList);
        Map<String, Integer> randomizedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            randomizedMap.put(entry.getKey(), entry.getValue());
        }
        return randomizedMap;
    }
    public void addImage(Context context, String imageName, int drawableId) {
        imagePaths.put(imageName, drawableId);
    }

    public int getImageResource(String imageName) {
        return imagePaths.get(imageName);
    }

    public List<String> getImageNames() {
        return new ArrayList<>(imagePaths.keySet());
    }

    public void inicializeMapPaths() {
        if (level.toLowerCase().contains("inicial"))
            initializeImagesInicialLevel(context);
        else if (level.toLowerCase().contains("pre intermedio"))
            initializePreIntermediateImages(context);
        else if (level.toLowerCase().contains("intermedio"))
            initializeIntermediateImages(context);
        else if (level.toLowerCase().contains("pre avanzado"))
            initializePreAdvancedImages(context);
        else if (level.toLowerCase().contains("avanzado"))
            initializeAdvancedImages(context);
        else
            initializeExpertImages(context);
    }

    public void inicializeALternativeWords() {
        alternativeWords = new ArrayList<>(Arrays.asList(
                "monk", "skin", "dolphin", "tiger", "lion",
                "apple", "water", "mango", "broccoli", "peach",
                "car", "glass", "train", "backpack", "bus",
                "phone", "computer", "cap", "radio", "camera",
                "run", "jump", "skip", "dance", "clap",
                "sing", "whistle", "read", "write", "paint",
                "lamp", "milk", "tennis", "walk", "rugby",
                "glasses", "backpack", "pencil", "book", "notebook",
                "shoe", "hat", "shirt", "shorts", "jacket",
                "cake", "cookie", "pie", "soda", "juice",
                "desk", "chair", "table", "sofa", "bed",
                "watch", "ring", "necklace", "bracelet", "earrings"
        ));
    }

    public void initializeImagesInicialLevel(Context context) {
        addImage(context, "avocado", R.drawable.avocado);
        addImage(context, "cow", R.drawable.cow);
        addImage(context, "duck", R.drawable.duck);
        addImage(context, "corn", R.drawable.corn);
        addImage(context, "carrots", R.drawable.carrots);
        addImage(context, "monkey", R.drawable.monkey);
        addImage(context, "grapes", R.drawable.grapes);
        addImage(context, "tomatoes", R.drawable.tomatoes);
        addImage(context, "lemon", R.drawable.lemon);
        addImage(context, "pear", R.drawable.pear);

        List<String> alternativeWords1 = new ArrayList<>(Arrays.asList( "avocato",   "avocada", "coww",   "crow",
                "dock",   "duch",    "carn",   "curn",  "carot",   "carots",   "monkay",
                "monke",    "graps",   "grabes",  "tomatos",  "tommatoes",  "lemmon",
                "leman",  "pearr", "peir", "apocado", "vocado", "kow",  "duke", "kern",
                "currots",   "munky", "grates", "tometoes", "lemn"
        ));
        alternativeWords.addAll(alternativeWords1);
    }

    public void initializePreIntermediateImages(Context context) {
        addImage(context, "blueberry", R.drawable.blueberry);
        addImage(context, "eggplant", R.drawable.eggplant);
        addImage(context, "cucumber", R.drawable.cucumber);
        addImage(context, "pineaple", R.drawable.pineaple);
        addImage(context, "strawberries", R.drawable.strawberries);
        addImage(context, "watermelon", R.drawable.watermelon);
        addImage(context, "potatoes", R.drawable.potatoes);
        addImage(context, "radish", R.drawable.radish);
        addImage(context, "donkey", R.drawable.donkey);
        addImage(context, "lettuce", R.drawable.lettuce);

        List<String> alternativeWords2 = new ArrayList<>(Arrays.asList(
                "blueberri", "bluebery", "blueberryy", "bluebrry",
                "eggplaint", "eggplantt", "eggplent", "egplant",
                "cucmber", "cumber", "cucumbber", "cucumbrr",
                "pineappl", "pineaple", "pineapp", "pineapplle",
                "strawberies", "strawberri", "strawbery", "strawbrries",
                "watermellon", "watermelonn", "watermellon", "watermellon",
                "potatoe", "potatoess", "potatoes", "potatos",
                "radishh", "radis", "radissh", "radishhh",
                "donkye", "donkeyy", "donkie", "donkkey",
                "lettus", "lettuice", "lettucee", "lettuces"
        ));
        alternativeWords.addAll(alternativeWords2);
    }

    public void initializeIntermediateImages(Context context) {
        addImage(context, "beetroot", R.drawable.beetroot);
        addImage(context, "grapefruit", R.drawable.grapefruit);
        addImage(context, "hedgehog", R.drawable.hedgehog);
        addImage(context, "fan", R.drawable.fan);
        addImage(context, "kick", R.drawable.kick);
        addImage(context, "run", R.drawable.run);
        addImage(context, "cry", R.drawable.cry);
        addImage(context, "drink", R.drawable.drink);
        addImage(context, "eat", R.drawable.eat);
        addImage(context, "swim", R.drawable.swim);

        List<String> alternativeWords3 = new ArrayList<>(Arrays.asList(
                "beetroot", "beetrote", "beatroot", "beetrot", "beatroote",
                "grapefruit", "grapfruit", "grapefruit", "grapefrut", "grapfrut",
                "hedgehog", "hedgehugg", "hedgehog", "hedghog", "hedghugg",
                "fan", "fann", "fain", "faen", "fen",
                "kick", "kik", "kikc", "kec", "keek",
                "run", "runn", "rn", "ruun", "runnn",
                "cry", "criy", "cryi", "cr", "cyr",
                "drink", "drinnk", "dring", "drin", "drrink",
                "eat", "et", "eet", "eatt", "eattt",
                "swim", "swimm", "swimmm", "swimn", "swmm"
        ));
        alternativeWords.addAll(alternativeWords3);

    }

    public void initializePreAdvancedImages(Context context) {
        addImage(context, "cut", R.drawable.cut);
        addImage(context, "cook", R.drawable.cook);
        addImage(context, "draw", R.drawable.draw);
        addImage(context, "fold", R.drawable.fold);
        addImage(context, "jump", R.drawable.jump);
        addImage(context, "sleep", R.drawable.sleep);
        addImage(context, "throw", R.drawable.throw_verb);
        addImage(context, "play", R.drawable.play_bat);
        addImage(context, "run", R.drawable.run);
        addImage(context, "kick", R.drawable.kick);

        List<String> alternativeWords4 = new ArrayList<>(Arrays.asList(
                "cut", "cuut", "cut", "cat", "cutt",
                "cook", "cookk", "cok", "cokk", "cookk",
                "draw", "daw", "daw", "drwa", "dra",
                "fold", "forld", "foldd", "foled", "foldd",
                "jump", "jummp", "jumpp", "jimp", "jup",
                "sleep", "slep", "sleeep", "slep", "slee",
                "throw", "thro", "thw", "throow", "throww",
                "play", "plai", "pllay", "pla", "plai",
                "run", "runn", "runn", "ron", "ruun",
                "kick", "kik", "kikc", "kicc", "kikc"
        ));
        alternativeWords.addAll(alternativeWords4);
    }

    public void initializeAdvancedImages(Context context) {
        addImage(context, "padlock", R.drawable.padlock);
        addImage(context, "stapler", R.drawable.stapler);
        addImage(context, "carry", R.drawable.carry);
        addImage(context, "drive", R.drawable.drive);
        addImage(context, "climb", R.drawable.climb);
        addImage(context, "mause", R.drawable.mause);
        addImage(context, "mix", R.drawable.mix);
        addImage(context, "watch", R.drawable.watch);
        addImage(context, "study", R.drawable.study);
        addImage(context, "think", R.drawable.think);

        List<String> alternativeWords5 = new ArrayList<>(Arrays.asList(
                "padlock", "padlok", "padllock", "paddlock", "padloc",
                "stapler", "stapleer", "stapplr", "stapler", "stapllr",
                "carry", "cary", "carr", "carri", "caryy",
                "drive", "driv", "drrive", "drve", "drivve",
                "climb", "climbb", "clib", "clim", "climbd",
                "mause", "mouse", "maus", "mausee", "mous",
                "mix", "mxi", "mik", "mixx", "miks",
                "watch", "wath", "wacth", "watchh", "wetch",
                "study", "studi", "stuy", "stady", "studyy",
                "think", "thnk", "thnkk", "thik", "thinkk"
        ));
        alternativeWords.addAll(alternativeWords5);
    }

    public void initializeExpertImages(Context context) {
        addImage(context, "grater", R.drawable.grater);
        addImage(context, "gopher", R.drawable.gopher);
        addImage(context, "beetroot", R.drawable.beetroot);
        addImage(context, "hang", R.drawable.hang);
        addImage(context, "grapefruit", R.drawable.grapefruit);
        addImage(context, "coconut", R.drawable.coconut);
        addImage(context, "yell", R.drawable.yell);
        addImage(context, "vacuum", R.drawable.vacuum);
        addImage(context, "heater", R.drawable.heater);
        addImage(context, "rainy", R.drawable.rainy);

        List<String> alternativeWords6 = new ArrayList<>(Arrays.asList(
                "grater", "graterz", "graterd", "grate", "graterx",
                "gopher", "gofer", "goper", "gofher", "gophr",
                "beetroot", "beetrrt", "beetrot", "beetrootd", "beetrootx",
                "hang", "hng", "hangg", "hagn", "hngg",
                "grapefruit", "grapfrut", "grapefruitd", "grapfruit", "grapefruit",
                "coconut", "coconutt", "coconout", "cocunut", "cocnut",
                "yell", "yel", "yelll", "yellk", "yellx",
                "vacuum", "vacum", "vacumm", "vacuumm", "vacumx",
                "heater", "heator", "heatrr", "heaterx", "hater",
                "rainy", "rainy", "rainyy", "rainn", "rayny"
        ));
        alternativeWords.addAll(alternativeWords6);
    }
}
