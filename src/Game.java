import java.util.Scanner;

import com.gachagame.adventure.AdventureHandler;
import com.gachagame.gacha.GachaHandler;
import com.gachagame.player.Player;
import com.gachagame.refinement.LevelHandler;
import com.gachagame.refinement.MergeHandler;

public class Game {
    private Player p;
    private AdventureHandler a;
    private GachaHandler g;
    private LevelHandler l;
    private MergeHandler m;
    private Scanner scan;
    private Boolean running;

    public Game() {
        this.p = new Player();
        this.a = new AdventureHandler();
        this.g = new GachaHandler();
        this.l = new LevelHandler();
        this.m = new MergeHandler();
        this.scan = new Scanner(System.in);
        this.running = true;
    } 
   
    public void run() {
        int userInput = -1;
        System.out.println("Welcome to MCO Pair 33 Gacha Simulator!");
        System.out.println("You begin with 1500 resources for your initial pulls.");
        
        while (this.running) {
            System.out.println(String.format("%-60s", " ").replace(" ", "-"));
            System.out.println(String.format("%34s", "Main Menu"));
            System.out.println(String.format("%-60s", " ").replace(" ", "-"));
            System.out.println("[1] Manage Inventory\n[2] Use Gacha Machine\n[3] Set Party Members\n[4] Go Adventuring\n[5] Merge Items\n[6] Level Up Items\n[7] Exit Game");

            while (userInput < 1 || userInput > 7) {
                while (!scan.hasNextInt()) {
                    scan.next();
                }
                userInput = scan.nextInt();
            }

            if (userInput == 1) {
                p.manageInventory();
            } else if (userInput == 2) {
                p.pull(this.g);
            } else if (userInput == 3) {
                p.setPartyMembers();
            } else if (userInput == 4) {
                p.adventure(this.a);
            } else if (userInput == 5) {
                p.merge(this.m);
            } else if (userInput == 6) {
                p.level(this.l);
            } else if (userInput == 7) {
                stop();
            }

            // reset input after game loop cycle
            userInput = -1;
        }
    }

    public void stop() {
        int userInput = -1;
        System.out.println("Are you sure? You will lose all your progress!\n[1] Yes\n[2] No");
        while (userInput < 1 || userInput > 2) {
            while (!scan.hasNextInt()) {
                scan.next();
            }
            userInput = scan.nextInt();
        }

        if (userInput == 2) {
            return;
        } else {
            System.out.println("Thank you for playing!");
            scan.close();
            this.running = false;
        }
    }
}
