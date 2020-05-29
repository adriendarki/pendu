import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.*;


// Ce pendu a été optimisé de sorte à être plus fun à jouer
public class Play {
  
    public static final String[] QUOTES =
            {
            "La pendaison est le fait de suspendre une personne, au moyen généralement d'une corde, mais aussi parfois de chaînes, par le cou ou par d'autres parties du corps.",
            "La pendaison entraîne une rupture du cou ou une suffocation, une impossibilité de respirer et enfin la mort.",
            "En tant que moyen d'homicide, la pendaison est attestée dans la plupart des civilisations, car entraînant une mort rapide.",
            "Dans le cas des condamnations à mort, la pendaison a un aspect exemplaire car elle se pratiquait en public \n" +
                    "et le corps restait sur le lieu de la pendaison plusieurs jours accroché à son gibet.",
            "Il existe deux types de pendaison : avec chute (comme les meilleures blagues, ce sont les plus courtes) et sans chute",
            "Une petite subtilité, c'est qu'il y a des pendaisons complètes et des non-complètes. Dans ce dernier cas, les pieds ne touchent pas le sol.",
            "L'arrêt net de la chute par la corde provoque une rupture des vertèbres cervicales, \n" +
                    "l'arrachement de l'extrémité supérieure de la moelle épinière (le tronc cérébral) provoque \n" +
                    "une atteinte des centres nerveux qui commandent la respiration",
            "La pendaison sans chute, (ou de faible hauteur) n'est pas un moyen efficace : la compression des veines jugulaires empêchant le retour du sang \n" +
                    "depuis la tête vers le cœur, d'où un œdème et une cyanose visibles au niveau de la face et de la langue,\n" +
                    "et un œdème cérébral entraînant une perte de connaissance assez lente suivie d'un décès assez tardif",
            "Les soldats nazis avaient élaboré, pendant la Seconde Guerre mondiale, une technique de pendaison lente qui consistait à attacher \n" +
                    "l'une des jambes du supplicié afin qu'elle ne touche pas le sol,\n" +
                    "laissant à l'autre le soin de supporter à elle seule le poids du corps.",
            "Mais on a relaté certaines exécutions durant lesquelles cette technique n'eut pas le résultat escompté :\n" +
                    "soit le condamné eut la tête arrachée à cause d'une trop grande énergie cinétique accumulée lors de la chute (corde trop longue),\n" +
                    "soit celui-ci fut tout simplement étranglé par le fait que cette énergie cinétique était trop faible (corde trop courte)",
            "L'échafaud — comme, plus tard, la guillotine — fut surnommé « l’Abbaye de Monte-à-Regret ».\n" +
                    "Cette expression date du xviie siècle. Nous la comprenons aujourd'hui avec son terme « regret »\n" +
                    "(on va rarement volontiers à une exécution quand on en est le principal acteur),\n" +
                    "mais elle vient plus probablement de « à regrès », qui voulait dire « à reculons », car on faisait monter le condamné à l'envers\n" +
                    "vers la potence avant de lui passer la cravate de chanvre autour du cou.",
            "Pendant la Révolution française, mettre à la lanterne, s'est dit populairement \n" +
                    "pour pendre aux cordes d'un réverbère ceux que désignait la fureur populaire."
            };
    public static final String[] QUOTESAMBIANCE =
            {
                    "*Le bourreau vous lance un regard gourmand...gênant...*",
                    "*La foule vous insulte copieusement. Les ingrats.*",
                    "*Vous vous rappelez de vos parents vous disant que Pirate était un piètre choix de carrière ?*",
                    "*Hadopi : 1. Vous : 0*",
                    "*Vous n'avez jamais été très sportif mais c'est l'occasion de montrer votre meilleur jeu de jambes !*",
                    "*Michel avait raison : 'philosopher, c'est apprendre à mourir'*",
                    "*Le parfum de cette corde en chanvre est étonnament agréable. Quelqu'un a du feu ?*",
                    "*Une pensée vous vient : quand vos pieds descendront, votre âme montera.*",
                    "*Si seulement vous pouviez vous gratter le nez...Ah merci m'dame pour cette tomate pourrite !*",
                    "*'Touchez ma bosse...non, pas celle-là', c'est peut-être à cause de cette répartie que vous en êtes là...*",
                    "*Et dire qu'il y a des manspreaders qui n'iront jamais à l'échaffaud*",
                    "*La présence d'un autre condamné à vos côtés vous fait chaud au coeur. Un SJW apparemment.*",
                    "Quelle indignité !! (si seulement vous aviez des talonnettes...)",
                    "Ok Google : Fraude fiscale ministre. Google : Qu'a eu Zack en 2015 ?",
                    "'Coucou ! Tu veux voir mes bytes ?' ... Vous n'auriez jamais dû voler et avaler ces nanites...",
            };



public class Play extends Word {

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
    private String nextQuote() {
        return QUOTES[RANDOM.nextInt(QUOTES.length)];
    }
    private String nextQuoteAmbiance() {
        return QUOTESAMBIANCE[RANDOM.nextInt(QUOTESAMBIANCE.length)];
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

                //Petite citation
                System.out.println("\nVous mourrez moins bêtes (mais vous mourrez comme même):");
                System.out.println(nextQuote());
                System.out.println("\n--------------------------- ENTREZ UNE LETTRE ---*");
                System.out.println(nextQuoteAmbiance());

                String str = input.next();
                str = str.toUpperCase();
                if (str.length() > 1) {
                    str = str.substring(0, 1);
                }
                enter(str);
                System.out.println("\n" + wordFoundContent());
                drawGallows();
                if (wordFound()) {
                    System.out.println("\nC'est gagné ! You did it !");
                    break;
                } else {
                    System.out.println("\n=> Il ne vous reste plus que " + (MAX_ERRORS - nbErrors) + " chances...") ;
                }
            }

            if (nbErrors == MAX_ERRORS) {
                System.out.println("\nPendu !");
                System.out.println("=> C'était pourtant simple. Il fallait trouver : " + wordToFind);
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
        System.out.println("\n \n   ooooooooo.                               .o8                   .o. \n" +
                "  `888   `Y88.                            \"888                   888 \n" +
                "   888   .d88'  .ooooo.  ooo. .oo.    .oooo888  oooo  oooo       888 \n" +
                "   888ooo88P'  d88' `88b `888P\"Y88b  d88' `888  `888  `888       Y8P \n" +
                "   888         888ooo888  888   888  888   888   888   888       `8' \n" +
                "   888         888    .o  888   888  888   888   888   888       .o. \n" +
                "  o888o        `Y8bod8P' o888o o888o `Y8bod88P\"  `V88V\"V8P'      Y8P \n" +
                "                                                                             " +
                "                                                                             " +
                "                                                                             ");

        System.out.println("     .@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@               \n" +
                "    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@               \n" +
                "     .&&&&&&@@@&&&&&&&&&&&&&@@@@@@@@&&&&&&&&&&&&&&&&&&&B@@@M               \n" +
                "            L@/               %@@@@@@@                 L@@@E               \n" +
                "            @@/                 .@@@@@@@,              A@@@D               \n" +
                "            R@/                    #@@@@@@&            C@@@E               \n" +
                "            @@/                      .@@@@@@@,         K@@@F               \n" +
                "            E@/                         %@@@@@@&       R@@@@               \n" +
                "            @E/                           .@@@@@@@,    O@@@@               \n" +
                "    #@@@@%. M@/                              #@@@@@@&  C@@@@               \n" +
                "  @@@@@@@@@@@@/                                .@@@@@@@K@@@@               \n" +
                " @@@@@@@@@@@@@/                                   #@@@@@@@@@               \n" +
                " *@@@@@@@@@@@@@@&*                                  .@@@@@@@               \n" +
                "   (@@@@@@@@@@@@@@@@                                   @@@@@               \n" +
                "     ,@@@@@@@@@@@@@@@&                                 @@@@@               \n" +
                "    &@@@@@@R@F@@@@@@@@@                                @@@@@               \n" +
                "   @@@@@@@@@G@J@@@%@@@@@                               @@@@@               \n" +
                "  @@@@@#%@@@@@@@@@ @@@@@(                              @@@@@               \n" +
                " @@@@@& %@@@@@@@@@ ,@@@@@                              @@@@@               \n" +
                " @@@@@  %@@@@@@@@@  @@@@@.                             @@@@@               \n" +
                " @@@@@  %@@@@@@@@@  &@@@@*                             @@@@@               \n" +
                " @@@@@  #@@@@@@@@@  ,@@@@,                             @@@@@               \n" +
                "        #@@@@@@@@@                                     @@@@@               \n" +
                "        &@@@@@@@@@                                    @@@@@@(              \n" +
                "        @@@@@@@@@@                                   @V@E@R@N@             \n" +
                "       ,@@@F@I@@@@                                  @@@@@@@@@@@            \n" +
                "       #@@@@#@@@@@.                                @M@C@R0N@@@@@.          \n" +
                "       @@@@@,@@@@@*                               @@@@@B@U@Z@I@N@/         \n" +
                "       @@@@@ %@@@@#                             ,@@@@@,@@@@@ @@@@@%        \n" +
                "       %@@@# *@@@@#                            /@@@@@. @@@@@  @@@@@@       \n" +
                "                                              #@@@@@   @@@@@   %@@@@@      \n" +
                "                                             ,@@@@@    @@@@@    /@@@@@     \n");
        System.out.println("\n ------------------------- An original game by -----------------------");
        System.out.println("  \\---------*=============================================*---------/");
        System.out.println("    \\-------|>  A.Bouthet - E.Ziegelmeger - V.Fouillade  <|-------/");
        System.out.println("      \\-----*=============================================*-----/");


        System.out.println("             + ON EST GAI LE MATIN, ON EST PENDU LE SOIR +       " +
                "\n            - Voltaire // Charlot ou la Comtesse de Givry - ");

        System.out.println("                 'Huit essais, pas un de plus...'       ");

        Play hangmanGame = new Play();
        hangmanGame.newGame();
        hangmanGame.play();
    }

}
