import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Play {

    // Java Keywords
    public static final String[] WORDS = {
            "ABSTRACT", "ASSERT", "BOOLEAN", "break", "byte",
            "CASE", "CATCH", "CHAR", "CLASS", "CONST",
            "CONTINUE", "DEFAULT", "DOUBLE", "DO", "ELSE",
            "ENUM", "VLADIMIR", "FALSE", "FINAL", "FINALLY",
            "FLOAT", "FOR", "GOTO", "IF", "IMPLEMENTS",
            "IMPORT", "INSTANCEOF", "INT", "INTERFACE",
            "LONG", "NATIVE", "NEW", "NULL", "PACKAGE",
            "PRIVATE", "PROTECTED", "PUBLIC", "RETURN",
            "SHORT", "STATIC", "STRICTFP", "SUPER", "SWITCH",
            "SYNCHRONIZED", "THIS", "THROW", "THROWS",
            "TRANSIENT", "TRUE", "TRY", "VOID", "VOLATILE", "WHILE"
    };

    public static final Random RANDOM = new Random();
    public static final int MAX_ERRORS = 9;
    private int incGuessCount = 0;
    private String wordToFind;
    private char[] wordFound;
    private int nbErrors;
    private final ArrayList < String > letters = new ArrayList < > ();
    private String nextWordToFind() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    public void newGame() {
        nbErrors = 0;
        letters.clear();
        wordToFind = nextWordToFind();


        wordFound = new char[wordToFind.length()];

        Arrays.fill(wordFound, '_');
    }

    public boolean wordFound() {
        return wordToFind.contentEquals(new String(wordFound));
    }

    private void enter(String c) {
        if (!letters.contains(c)) {
            if (wordToFind.contains(c)) {
                int index = wordToFind.indexOf(c);
                while (index >= 0) {
                    wordFound[index] = c.charAt(0);
                    index = wordToFind.indexOf(c, index + 1);
                }
            } else {
                nbErrors++;
                incGuessCount++;
            }
            letters.add(c);
        }
    }

    private String wordFoundContent() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < wordFound.length; i++) {
            builder.append(wordFound[i]);

            if (i < wordFound.length - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public void play() {
        try (Scanner input = new Scanner(System.in)) {
            // we play while nbErrors is lower than max errors or user has found the word
            while (nbErrors < MAX_ERRORS) {
                System.out.println("\n           *--- ENTREZ UNE LETTRE ---*");
                String str = input.next();
                if (str.length() > 1) {
                    str = str.substring(0, 1);
                }
                enter(str);
                System.out.println("\n" + wordFoundContent());
                drawGallows();
                if (wordFound()) {
                    System.out.println("\nYou win!");
                    break;
                }
                else
                {
                    System.out.println("\n=> Nb tries remaining : " + (MAX_ERRORS - nbErrors));
                }
            }

            if (nbErrors == MAX_ERRORS ) {
                System.out.println("\nYou lose!");
                System.out.println("=> Word to find was : " + wordToFind);
            }
        }
    }
    //Hangman Draw
    public void defaultMan(){
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("      ");
        System.out.println("_|____");
    }
    public void oneMan(){
        System.out.println("        ");
        System.out.println("        ");
        System.out.println("        ");
        System.out.println(" |      ");
        System.out.println(" |      ");
        System.out.println("_|______");
    }
    public void twoMan(){
        System.out.println("        ");
        System.out.println(" |      ");
        System.out.println(" |      ");
        System.out.println(" |      ");
        System.out.println(" |      ");
        System.out.println("_|______");
    }
    public void threeMan(){
        System.out.println("_______ ");
        System.out.println(" |   |  ");
        System.out.println(" |      ");
        System.out.println(" |      ");
        System.out.println(" |      ");
        System.out.println("_|______");
    }
    public void fourMan(){
        System.out.println("_______ ");
        System.out.println(" |   |  ");
        System.out.println(" |   @  ");
        System.out.println(" |      ");
        System.out.println(" |      ");
        System.out.println("_|______");
    }
    public void fiveMan(){
        System.out.println("_______ ");
        System.out.println(" |   |  ");
        System.out.println(" |   @  ");
        System.out.println(" |   |  ");
        System.out.println(" |      ");
        System.out.println("_|______");
    }
    public void sixMan(){
        System.out.println("_______ ");
        System.out.println(" |   |  ");
        System.out.println(" |   @  ");
        System.out.println(" |  /|  ");
        System.out.println(" |      ");
        System.out.println("_|______");
    }
    public void sevenMan(){
        System.out.println("_______ ");
        System.out.println(" |   |  ");
        System.out.println(" |   @  ");
        System.out.println(" |  /|\\ ");
        System.out.println(" |      ");
        System.out.println("_|______");
    }
    public void heightMan(){
        System.out.println("_______ ");
        System.out.println(" |   |  ");
        System.out.println(" |   @  ");
        System.out.println(" |  /|\\ ");
        System.out.println(" |  /   ");
        System.out.println("_|______");
    }
    public void nineMan(){
        System.out.println("_______ ");
        System.out.println(" |   |  ");
        System.out.println(" |   @  ");
        System.out.println(" |  /|\\ ");
        System.out.println(" |  / \\ ");
        System.out.println("_|______");
    }
    public void drawGallows()
    {
        if(incGuessCount==0)
        {
            defaultMan();
        }

        if(incGuessCount==1)
        {
            oneMan();
        }

        if(incGuessCount==2)
        {
            twoMan();
        }

        if(incGuessCount==3)
        {
            threeMan();
        }

        if(incGuessCount==4)
        {
            fourMan();
        }

        if(incGuessCount==5)
        {
            fiveMan();
        }
        if(incGuessCount==6)
        {
            sixMan();
        }
        if(incGuessCount==7)
        {
            sevenMan();
        }
        if(incGuessCount==8)
        {
            heightMan();
        }
        if(incGuessCount==9)
        {
            nineMan();
        }
    }


    public static void main(String[] args) {
        System.out.println("\n \n ooooooooo.                               .o8                   .o. \n" +
                "`888   `Y88.                            \"888                   888 \n" +
                " 888   .d88'  .ooooo.  ooo. .oo.    .oooo888  oooo  oooo       888 \n" +
                " 888ooo88P'  d88' `88b `888P\"Y88b  d88' `888  `888  `888       Y8P \n" +
                " 888         888ooo888  888   888  888   888   888   888       `8' \n" +
                " 888         888    .o  888   888  888   888   888   888       .o. \n" +
                "o888o        `Y8bod8P' o888o o888o `Y8bod88P\"  `V88V\"V8P'      Y8P \n" +
                "                                                                             \n" +
                "                                                                             \n" +
                "                                                                             ");

        System.out.println("     .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@               \n" +
                "    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@               \n" +
                "     .&&&&&&@@@&&&&&&&&&&&&&@@@@@@@@&&&&&&&&&&&&&&&&&&&@@@@@               \n" +
                "            @@/               %@@@@@@@                 @@@@@               \n" +
                "            @@/                 .@@@@@@@,              @@@@@               \n" +
                "            @@/                    #@@@@@@&            @@@@@               \n" +
                "            @@/                      .@@@@@@@,         @@@@@               \n" +
                "            @@/                         %@@@@@@&       @@@@@               \n" +
                "            @@/                           .@@@@@@@,    @@@@@               \n" +
                "    #@@@@%. @@/                              #@@@@@@&  @@@@@               \n" +
                "  @@@@@@@@@@@@/                                .@@@@@@@@@@@@               \n" +
                " @@@@@@@@@@@@@/                                   #@@@@@@@@@               \n" +
                " *@@@@@@@@@@@@@@&*                                  .@@@@@@@               \n" +
                "   (@@@@@@@@@@@@@@@@                                   @@@@@               \n" +
                "     ,@@@@@@@@@@@@@@@&                                 @@@@@               \n" +
                "    &@@@@@@@@@@@@@@@@@@                                @@@@@               \n" +
                "   @@@@@@@@@@@@@@@%@@@@@                               @@@@@               \n" +
                "  @@@@@#%@@@@@@@@@ @@@@@(                              @@@@@               \n" +
                " @@@@@& %@@@@@@@@@ ,@@@@@                              @@@@@               \n" +
                " @@@@@  %@@@@@@@@@  @@@@@.                             @@@@@               \n" +
                " @@@@@  %@@@@@@@@@  &@@@@*                             @@@@@               \n" +
                " @@@@@  #@@@@@@@@@  ,@@@@,                             @@@@@               \n" +
                "        #@@@@@@@@@                                     @@@@@               \n" +
                "        &@@@@@@@@@                                    @@@@@@(              \n" +
                "        @@@@@@@@@@                                   @@@@@@@@@             \n" +
                "       ,@@@@@@@@@@                                  @@@@@@@@@@@            \n" +
                "       #@@@@#@@@@@.                                @@@@@@@@@@@@@.          \n" +
                "       @@@@@,@@@@@*                               @@@@@@@@@@@@@@@/         \n" +
                "       @@@@@ %@@@@#                             ,@@@@@,@@@@@ @@@@@%        \n" +
                "       %@@@# *@@@@#                            /@@@@@. @@@@@  @@@@@@       \n" +
                "                                              #@@@@@   @@@@@   %@@@@@      \n" +
                "                                             ,@@@@@    @@@@@    /@@@@@     \n");
        System.out.println("\n        *-------------An original game by-------------*");
        System.out.println("        *=============================================*");
        System.out.println("        |  A.Bouthet - E.Ziegelmeger - V.Fouillade    |");
        System.out.println("        *-------------°=================°-------------*");

        System.out.println("                 'Huit essais, pas un de plus...'       ");
        Play hangmanGame = new Play();
        hangmanGame.newGame();
        hangmanGame.play();
    }

}
