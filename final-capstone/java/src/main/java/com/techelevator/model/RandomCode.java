package com.techelevator.model;

import java.lang.reflect.Array;
import java.util.Random;

public class RandomCode {

    public String getInviteCode() {
       String letters = getRandomLetter();
       int numbers[] = getRandomNumsForInvite();
       String inviteCode = interleaveInvite(numbers, letters);
       return inviteCode;
    }

    private int getRandomNum(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }

    private String getRandomLetter() {
        String lettersForInvite = "";
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWYXZ";
        int randomIndex = getRandomNum(25);

        for (int i = 0; i < 3; i++) {
            lettersForInvite += alpha.charAt(randomIndex);
            randomIndex = getRandomNum(25);
        }
        return lettersForInvite;

    }

    private int[] getRandomNumsForInvite() {
        int[] numsForInvite = new int[3];
        int[] possibleNums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int randomIndex = getRandomNum(9);
        for (int i = 0; i < 3; i++) {
            numsForInvite[i] = possibleNums[randomIndex];
            randomIndex = getRandomNum(9);
        }
        return numsForInvite;
    }

    private String interleaveInvite(int[] nums, String letters) {
        String finalInvite = "";

        for (int i = 0; i < 3; i++) {
            finalInvite += letters.charAt(i);
            finalInvite += nums[i];
        }
        return finalInvite;
    }
}
