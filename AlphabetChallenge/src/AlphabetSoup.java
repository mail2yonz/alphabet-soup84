/**
 * YONATAN MENGESHA
 *Dear Mr/Miss
 *
 * This letter is to express my great interest in your company  for the position of an experienced Software Developer.
 * With a  Master’s degree in Information System management, and hands-on experience using java languages to create and implement software applications,
 * I am confident I will be an asset to your organization.
 *
 * I enjoy being challenged and engaging with projects that require me to work outside my comfort and knowledge set,
 * as continuing to learn new languages and development techniques are important to me and the success of your organization.
 *
 * I’ve attached a copy of my resume that details my projects and experience in software development.
 * I can be reached anytime via my cell phone, 240-938-7627 or via email yonatan.menegsha2017@gmail.com.
 *
 * Thank you for your time and consideration. I look forward to speaking with you about this opportunity.
 *
 * Sincerely,
 *
 * Yonatan mengesha
 * *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AlphabetSoup {
    static   int ROW;
    static  int  COL;
    static String line1;
    static Scanner scanner=null;



    public  static void main(String[] args) {


        try{

            scanner = new Scanner(new File("C:/Users/12409/Desktop/Assesement/AlphabetSoup.txt"));

            int linenum=0;
            String str;

            int count=0;

            List<String> charlist= new ArrayList<>();
            List<String> strArray= new ArrayList<>();
            while (scanner.hasNextLine()){
                // System.out.println(scanner.nextLine());
                str= scanner.nextLine();

                linenum++;
                if(str.contains("x")){
                    line1=str;
                }

                if(str.contains(" ")){
                    charlist.add(str.replace(" ",""));
                   // System.out.println(str);
                    count++;
                }


                if( linenum>count +1){
                    str=str.replace(" ","");
                    strArray.add(str);
                }
            }
            //for(int i=0;i<line1.length();i++){
            ROW=Character.getNumericValue(line1.charAt(0));
            COL=Character.getNumericValue(line1.charAt(2));

            //  }
            char[][] charMatrix= new char[ROW][COL];
            for(int i=0;i< ROW;i++)
            {
                for(int j=0;j<COL;j++)
                {
                    charMatrix[i][j]=charlist.get(i).charAt(j);

                }
            }
           // System.out.println(Arrays.deepToString(charMatrix));
            for(String str1:strArray){

                if(!str1.isEmpty()){
                    findWords(charMatrix,str1);
                   // System.out.println(str1);
                }

            }

        }catch(IOException e)
        {
            e.printStackTrace();
        }finally{
            scanner.close();
        }


    }

    // check whether given cell (row, col) is a valid
// cell or not.
    private  static boolean isvalid(int row, int col, int prevRow, int prevCol)
    {
        // return true if row number and column number
        // is in range
        return (row >= 0) && (row < ROW) &&
                (col >= 0) && (col < COL) &&
                !(row == prevRow && col == prevCol);
    }

    // These arrays are used to get row and column
// numbers of 8 neighboursof a given cell
    static int[] rowNum = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] colNum = {-1, 0, 1, -1, 1, -1, 0, 1};

    // A utility function to find the location of the character  for a 2D boolean
// matrix. It only considers the 8 neighbours as
// adjacent vertices
    private   static void FindLocation(char[][] matrix, int row, int col,
                                       int prevRow, int prevCol, String word,
                                       String path, int index)
    {
        String result="";
        // return if current character doesn't match with
        // the next character in the word
        if (index > word.length()-1 || matrix[row][col] != word.charAt(index))
            return;

        // append current character position to path
            if(index==0 || index==word.length()-1){
                path +=  String.valueOf(row)
                        + ":" + String.valueOf(col) +" ";
                result=word+" " +path;
            }


      //  }

        // current character matches with the last character
        // in the word
        if (index == word.length()-1)
        {
            System.out.print(result +"\n");
            return;
        }

        // Recur for all connected neighbours
        for (int k = 0; k < 8; ++k)
            if (isvalid(row + rowNum[k], col + colNum[k],
                    prevRow, prevCol))

                    FindLocation(matrix, row + rowNum[k], col + colNum[k],
                            row, col, word, path, index + 1);

    }

    // Searches given word in a given
    // matrix in all 8 directions
    private static void findWords(char mat[][], String word )
    {
        // traverse through the all cells of given matrix
        for (int i = 0; i < ROW; ++i)
            for (int j = 0; j < COL; ++j)

                // occurrence of first character in matrix
                if (mat[i][j] == word.charAt(0))

                    // check and print if path exists
                    FindLocation(mat, i, j, -1, -1, word, "", 0);
    }



}
