package com.techelevator;

public class ScratchPad {

    public static void main(String[] args) {
        int[] nums= {1,2,3,4,5};
        boolean has1and2=false;

        for(int i:nums){
            boolean has1=false;

            if(i==1){
                has1=true;
            }

            if(has1){

                if(i==2){
                    has1and2=true;
                }
            }
        }




    }
//    public boolean has12(int[] nums){
//        boolean has1and2=false;

//        for(int i:nums){
//            boolean has1=false;
//
//            if(nums[i]==1){
//                has1=true;
//            }
//
//            if(has1){
//
//                if(nums[i]==2){
//                    has1and2=true;
//                }
//            }
//        }
//        return has1and2;
//    }
}
