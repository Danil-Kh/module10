package org.example;

public class Words implements Comparable<Words> {
    String word;
    int counter;
    public Words(String word, int counter) {
        this.word = word;
        this.counter = counter;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Words validWord = (Words) o;
        return word.equals(validWord.word);
    }
    @Override
    public String toString() {
        return "\n" + "Word: |" + word + "| frequent " + counter + "\n";
    }
    @Override
    public int compareTo(Words o) {
        return o.counter - this.counter;
    }
}

