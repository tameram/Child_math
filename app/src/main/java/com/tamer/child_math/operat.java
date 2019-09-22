package com.tamer.child_math;

import android.util.Log;

import java.util.Random;

public class operat {
    private static final int NUMBER_OF_QUESTIONS = 10;
    private static final int MIN_QUESTION_ELEMENTS = 2;
    private static final int MAX_QUESTION_ELEMENTS = 4;
    private static final int MIN_QUESTION_ELEMENT_VALUE = 1;
    private static final int MAX_QUESTION_ELEMENT_VALUE = 100;
    private final Random randomGenerator = new Random();
    private static final String addition = "+";
    private static final String subtract = "-";
    private static final String multiply = "*";
    private static final String divide = "/";
    private static final int additionn = 1;
    private static final int subtractn = 2;
    private static final int multiplyn = 3;
    private static final int dividen = 4;
    private int correctAnswers = 0 ;
    private int addOper =0, subOper =0, multOper=0 , divOper=0;


    public boolean ifItCorrect (int answer , int correctAnswer){
        if(answer == correctAnswer)
            return true;
        return false;
    }
    public int randomNum(){
        int random = 0 ;
        Random rand = new Random();

// Obtain a number between [0 - 99].
         random = rand.nextInt(99);
         random+=1;
        return random ;
    }

    public int randomSmaleNum(){
        int random = 0 ;
        Random rand = new Random();

// Obtain a number between [0 - 9].
        random = rand.nextInt(9);
        return random ;
    }

    public int getAnswer(int answer){
        return answer;

    }


    public int operat() {
        int random = 0;
        Random rand = new Random();

// Obtain a number between [0 - 3].
        random = rand.nextInt(4);

        random += 1;

        return random ;
    }


    public String operator(int random){

        String oper = "";
        switch (random) {
            case 1:
                oper = addition;
                addOper++;
                break;
            case 2 :
                oper = subtract;
                subOper++;
                break;
            case 3 :
                oper = multiply;
                multOper++;
                break;
            case 4 :
                oper = divide;
                divOper++;
                break;
        }
        return oper;
    }

    public int theResult1(int num1 , int num2 , int oper){
        int result = 0 ;
        switch (oper){
            case 1:
                result = num1 + num2;

                break;
            case 2 :
                result = num1 - num2;

                break;
            case 3 :
                result = num1 * num2;

                break;
            case 4 :
                result = num1 / num2;

                break;

        }
        return result;
    }

    public int theResult2(int num1 , int num2 ,int num3 ,  int oper1 , int oper2){
        int result = 0 ;
        int tmp1 = 0 ;
        int tmp2 =0 ;
        int numHelper = 0 ;
        if(oper1 == 3 || oper1 == 4)
            tmp1=1;
        if(oper2 == 3 || oper2 == 4)
            tmp2=1;


        if(tmp1==0 && tmp2==1){
            numHelper= theResult1(num2,num3,oper2);
            result = theResult1(num1,numHelper,oper1);
        }
        else{
            numHelper= theResult1(num1,num2,oper1);
            result = theResult1(numHelper,num3,oper2);
        }

        return result;
    }

    private int theResult3(int num1, int num2, int num3, int num4, int operati1, int operati2, int operati3) {
        int result = 0 ;
        int tmp1 = 0 ;
        int tmp2 =0 ;
        int tmp3 = 0;

        if(operati1 == 3 || operati1 == 4)
            tmp1=1;
        if(operati2 == 3 || operati2 == 4)
            tmp2=1;
        if(operati3 == 3 || operati3 == 4)
            tmp3=1;

        int numHelper1 = 0 ;
        int numHelper2 = 0 ;

        if(tmp1 == tmp2 && tmp2==tmp3){// (+ + + ) or (* * * )
            numHelper1 = theResult2(num1 , num2 , num3 , operati1 , operati2);
            result = theResult1(numHelper1,num4,operati3);
        }
        else if(tmp1 == 0 && tmp3 == 1){// (+ ? * )
           numHelper1 = theResult1(num3 , num4 , operati3);
           numHelper2 = theResult1(num2 , numHelper1 , operati2);
           result = theResult1(num1 , numHelper2 , operati1);
        }
        else if(tmp1 == 1 && tmp3 == 0){// (* ? + )
            numHelper1 = theResult1(num1 , num2 , operati1);
            numHelper2 = theResult1( numHelper1 ,num3 , operati2);
            result = theResult1( numHelper2 , num4 ,operati3);
        }
        else if(tmp1 == 1 && tmp2 == 0 && tmp3 == 1){//(* + *)
            numHelper1 = theResult1(num1 , num2 , operati1);
            numHelper2 = theResult1( num3 ,num4 , operati3);
            result = theResult1( numHelper1 , numHelper2 ,operati2);
        }
        else if(tmp1 == 0 && tmp2 == 1 && tmp3 == 0){//(+ * +)
            numHelper1 = theResult1(num2 , num3 , operati2);
            numHelper2 = theResult1( num1 ,numHelper1 , operati1);
            result = theResult1( numHelper2 , num4 ,operati3);
        }
        return  result;
    }

    public String theQuestionWithTowNum(int add , int sub , int multi , int divide){

        if(add == 1)
            add = 1;
        else
            add = 0;

        if(sub == 1)
            sub = 2;
        else
            sub = 0;

        if(multi == 1)
            multi = 3;
        else
            multi = 0;

        if(divide == 1)
            divide = 4;
        else
            divide = 0;

        int num1 = randomNum();
        int num2 = randomNum();
        int operati =operat();// 1 2 3 4
        while (operati!=add && operati != sub && operati != multi && operati != divide)
            operati = operat();

        if(operati == multi){
            num1 = randomSmaleNum();
            num2 = randomSmaleNum();
        }

        while (operati == sub && num1 < num2){
            num1 = randomNum();
            num2 = randomNum();
        }
        while (operati == divide && num1 % num2 != 0){
            num1 = randomNum();
            num2 = randomNum();
        }

        /*while (ok){
            if(operati!= add || operati != sub || operati != multi || operati != divide)
                 operati = operat();
            else
                ok = false;
        }*/
        String oper = operator(operati);
        correctAnswers = theResult1(num1 , num2 , operati);
        return num1+oper+num2+"=";
    }
    public String theQuestionWithThreeNum(int add , int sub , int multi , int divide){
        if(add == 1)
            add = 1;
        else
            add = 0;

        if(sub == 1)
            sub = 2;
        else
            sub = 0;

        if(multi == 1)
            multi = 3;
        else
            multi = 0;

        if(divide == 1)
            divide = 4;
        else
            divide = 0;

        int num1 = randomNum();
        int num2 = randomNum();
        int num3 = randomNum();
        int operati1 =operat();
        int operati2 =operat();
        while (operati1!=add && operati1 != sub && operati1 != multi && operati1 != divide)
            operati1 = operat();
        while (operati2!=add && operati2 != sub && operati2 != multi && operati2 != divide)
            operati2 = operat();

        if(operati1 == multi){
            num1 = randomSmaleNum();
            num2 = randomSmaleNum();
        }
        if(operati2 == multi){
            num2 = randomSmaleNum();
            num3 = randomSmaleNum();
        }
        while (operati1 == sub && num1 < num2){
            num1 = randomNum();
            num2 = randomNum();
        }
        while (operati2 == sub && num2 < num3){
            num2 = randomNum();
            num3 = randomNum();
        }

        while (operati1 == divide && operati2 == divide && num1 % num2 != 0 && theResult1(num1,num2,divide) % num3 != 0){
            num1 = randomNum();
            num2 = randomNum();
            num3 = randomNum();
        }
       /* while (operati1 != divide && operati2 == divide &&  num2 % num3 != 0){

            num2 = randomNum();
            num3 = randomNum();
        }
        while (operati1 == divide && operati2 != divide && num1 % num2 != 0 ){
            num1 = randomNum();
            num2 = randomNum();

        }*/

        String oper1 = operator(operati1);
        String oper2 = operator(operati2);
        correctAnswers = theResult2(num1 ,num2 , num3 , operati1, operati2);
        return num1+oper1+num2+oper2+num3+"=";
    }
    public String theQuestionWithFourNum(int add , int sub , int multi , int divide){
        if(add == 1)
            add = 1;
        else
            add = 0;

        if(sub == 1)
            sub = 2;
        else
            sub = 0;

        if(multi == 1)
            multi = 3;
        else
            multi = 0;

        if(divide == 1)
            divide = 4;
        else
            divide = 0;

        int num1 = randomNum();
        int num2 = randomNum();
        int num3 = randomNum();
        int num4 = randomNum();
        int operati1 =operat();
        int operati2 =operat();
        int operati3 =operat();
        while (operati1!=add && operati1 != sub && operati1 != multi && operati1 != divide)
            operati1 = operat();
        while (operati2!=add && operati2 != sub && operati2 != multi && operati2 != divide)
            operati2 = operat();
        while (operati3!=add && operati3 != sub && operati3 != multi && operati3 != divide)
            operati3 = operat();

        if(operati1 == multi){
            num1 = randomSmaleNum();
            num2 = randomSmaleNum();
        }
        if(operati2 == multi){
            num2 = randomSmaleNum();
            num3 = randomSmaleNum();
        }
        if(operati3 == multi){
            num3 = randomSmaleNum();
            num4 = randomSmaleNum();
        }

        while (operati1 == sub && num1 < num2){
            num1 = randomNum();
            num2 = randomNum();
        }
        while (operati2 == sub && num2 < num3){
            num2 = randomNum();
            num3 = randomNum();
        }
        while (operati3 == sub && num3 < num4){
            num3 = randomNum();
            num4 = randomNum();
        }
        /*while (operati1 == divide && num1 % num2 != 0){
            num1 = randomNum();
            num2 = randomNum();
        }
        while (operati2 == divide && num2 % num3 != 0){
            num1 = randomNum();
            num2 = randomNum();
        }
        while (operati3 == divide && num3 % num4 != 0){
            num1 = randomNum();
            num2 = randomNum();
        }*/
        String oper1 = operator(operati1);
        String oper2 = operator(operati2);
        String oper3 = operator(operati3);
        correctAnswers = theResult3(num1 , num2 , num3 , num4 , operati1, operati2, operati3);
        return num1+oper1+num2+oper2+num3+oper3+num4+"=";
    }




    public String randomQuestion(int level ,int add , int sub , int multi , int divide) {
        int ran = 0;
        Random rand = new Random();

// Obtain a number between [0 - 3].

        if(level == -1 || level == 3)
            ran = operat();
        else if(level == 1)
            ran = level;
        else if(level == 2){
            ran = rand.nextInt(2);
            ran += 1;
        }

        String ques = "";
        switch (ran) {
            case 1:
                ques = theQuestionWithTowNum(add , sub , multi , divide);

                break;
            case 2:
                ques =theQuestionWithThreeNum(add , sub , multi , divide);

                break;
            case 3:
                ques =theQuestionWithFourNum(add , sub , multi , divide);

        }
        // if > 0 && is not double just be int

        //if(correctAnswers < 0 )
          //  randomQuestion(-1);
        return ques;
    }


    public void theQuestion(int level , int add , int sub , int multi , int divide){

    }

    public int getAddOper() {
        return addOper;
    }

    public int getSubOper() {
        return subOper;
    }

    public void setSubOper(int subOper) {
        this.subOper = subOper;
    }

    public int getMultOper() {
        return multOper;
    }

    public int getDivOper() {
        return divOper;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
