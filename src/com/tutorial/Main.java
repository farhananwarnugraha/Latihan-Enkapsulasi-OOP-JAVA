package com.tutorial;


class Player{
    private String name;
    private int baseHealth;
    private int baseAttack;
    private int level;
    private int incrementHealth;
    private int incrementAttack;
    private int totalDamage;
    private boolean isAlive;
    //ini object member
    private Armour armour; 
    private Weapon weapon;

    public Player(String name){
        this.name = name;
        this.baseHealth = 100;
        this.baseAttack = 100;
        this.level = 1;
        this.incrementHealth = 20;
        this.incrementAttack = 20;
        this.isAlive = true;
    } 

    public String getName(){
        return this.name;

    }

    public int getHelath(){
        return this.maxHealth() - this.totalDamage;
    }

    public void setArmour(Armour armour){
        this.armour = armour;
    }

    public void display(){
        System.out.println("Player: " + this.name);
        System.out.println("Level: " + this.level);
        System.out.println("Health: " + this.getHelath() + "/" + this.maxHealth());
        System.out.println("Attack :" + this.getAttackPower());
        System.out.println("Status :" + this.isAlive + "\n");
    }

    public void attack(Player opponent){
        //hitung damage
        int damage = this.getAttackPower();
        //print event
        System.out.println(this.name + " is attacking " + opponent.getName() + " with " + damage );
        //attact opponent => dengan mengambil void defence dan attact
        opponent.defence(damage);

        this.levelUp();
    }

    public void defence(int damage){

        //delta damage => damage - armour
        int defencePower = this.armour.getDefencePower();
        int deltaDamage;

        System.out.println(this.name + "defance Power = " + defencePower);
        
        if (damage > defencePower) {
            deltaDamage = damage - defencePower;
        }else{
            deltaDamage = 0;
        }
        System.out.println("Damage earned = " + deltaDamage + "\n");
        //adding total damage
        this.totalDamage +=deltaDamage;
        //check is alive
        if (this.getHelath() <= 0) {
            this.isAlive = false;
            this.totalDamage = this.maxHealth();
        }

        this.display();
    }

    private void levelUp(){
        this.level++;
    }

    private int getAttackPower(){
        return this.baseAttack + this.level*this.incrementAttack + this.weapon.getAttack();
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public int maxHealth(){
        //total health = base health + sternght * 10
        return this.baseHealth+this.level*this.incrementHealth + this.armour.getAdddHealth();
    }

}

class Weapon{
    private String name;
    private int attack;

    public Weapon(String name, int attack){
        this.name = name;
        this.attack = attack;
    }

    public int getAttack(){
        return this.attack;
    }
}

class Armour{
    private String name;
    private int strength;
    private int health;

    public Armour(String name, int strength, int health){
        this.name = name;
        this.strength = strength;
        this.health = health;
    }  

    public int getAdddHealth(){
        return this.strength*10 + this.health;
    }

    public int getDefencePower(){
        return this.strength*2;
    }
}
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("Marni");
        Armour armour1 = new Armour("Baju Besi", 5, 100);
        Weapon weapon1 = new Weapon("Pedang", 10);
        player1.setWeapon(weapon1);
        player1.setArmour(armour1);

        Player player2 = new Player("Isabela");
        Armour armour2 = new Armour("Gamis", 1, 40);
        Weapon weapon2 = new Weapon("Pecut", 40);
        player2.setWeapon(weapon2);
        player2.setArmour(armour2);

        player1.display();
        player2.display();

        player1.attack(player2);
        player2.attack(player1);
        player2.attack(player1);
        
    }
}