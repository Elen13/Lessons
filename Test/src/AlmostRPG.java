import java.util.ArrayList;
import java.util.Random;

public class AlmostRPG {
}

class Item{
    int price, weight, type;
    public Item(int price, int weight, int type){
        this.price = price;
        this.weight = weight;
        this.type = type;
    }
}

class Enemy{
    int hp, damage;
    int exp;
    public Enemy(int hp, int damage, int exp){
        this.hp = hp;
        this.damage = damage;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "hp=" + hp +
                ", damage=" + damage +
                ", exp=" + exp +
                '}';
    }
}

abstract class Hero{

    protected String nickName;
    protected int level;
    protected int s, a, i, exp, hp, mana; // сила ловкость интеллект опыт здоровье мана
    protected int x, y; // координаты героя на карте
    protected ArrayList<Item> items = new ArrayList<>(); // список поднятых предметов
    protected int damage; // урон

    public int getMana(){
        return mana;
    }
    public int getDamage(){
        return damage;
    }
    public int getHp(){
        return hp;
    }
    public void goToCursor(int xx, int yy){
        x = xx; y = yy;
    }

    public abstract void levelUp();

    public abstract void attack(Enemy enemy); // этот метод должнен быть определен в классах наследниках

    public abstract void defense(Enemy enemy);// этот метод должнен быть определен в классах наследниках

    public void openItem(Item item){
        // метод должен добавлять предмет в список с вероятностью 50 %
        // для осуществления вероятностных процессов можно использовать случайное число от 0 до 100.
        Random random = new Random();
        int r = random.nextInt(100);
        if (r > 50) {
            items.add(item);
        }
    }
}

class Warrior extends Hero{
    public Warrior(){
        //базовые значение
        this.level = 0;
        this.hp = 500;
        this.mana = 10;
        this.s = 100;
        this.a = 50;
        this.i = 1;
        this.exp = 0;
        this.damage = 150;
    }

    @Override
    public void levelUp() {
        this.level++;
        this.hp += 200;
        this.mana += 10;
        this.s += 10;
        this.a += 10;
        this.i += 10;
        this.damage += 20;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "nickName='" + nickName + '\'' +
                ", level=" + level +
                ", s=" + s +
                ", a=" + a +
                ", i=" + i +
                ", exp=" + exp +
                ", hp=" + hp +
                ", mana=" + mana +
                ", damage=" + damage +
                '}';
    }

    @Override
    public void attack(Enemy enemy) {

        for(Item item : items){
            if(item.type == 1)
                this.damage += 100;
        }

        enemy.hp -= this.damage;      // warrior attack
        if(enemy.hp <= 0){                      // if warrior win
            if(this.exp + enemy.exp >= 500){    // if warrior get new level
                this.exp = this.exp + enemy.exp;
                if(this.exp >= 500){
                    while (this.exp >= 500){
                        this.exp -= 500;
                        levelUp();
                    }
                }
                else
                    levelUp();
            }
            else
                this.exp += enemy.exp;
        }
        else{                                   // enemy attack
            this.hp -= enemy.damage;
            if(this.hp <= 0){
                System.out.println("Ваш герой был убит");
            }
        }
    }

    @Override
    public void defense(Enemy enemy) {

    }
            /*
класс должен обладать всеми свойствами героя при создании воин должен обладать следующими характеристиками:
 здоровье - 500, мана - 10, сила - 100, ловкость - 50, интеллект - 1, опыт 0, урон - 150. При атаке герой наносит цели урон и получает урон в ответ от цели,
 действия повторяются пока кто то не победит.
 В случае победы герой получает опыт цели, каждые 500 единиц опыта герой получает новый уровень.
 Новый уровень дает +10 ко всем характеристикам, +200 здоровья и +20 урона.Если воин имеет предмет 1 типа, к его урону добавляется 100.
            Уже мертвая цель - урона герою не наносит!!!
            */
}

class Archer extends Hero{
    public Archer(){
        //базовые значение
        this.hp = 200;
        this.mana = 50;
        this.s = 20;
        this.a = 150;
        this.i = 30;
        this.exp = 0;
        this.damage = 200;
    }

    @Override
    public void openItem(Item item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Archer{" +
                "nickName='" + nickName + '\'' +
                ", level=" + level +
                ", s=" + s +
                ", a=" + a +
                ", i=" + i +
                ", exp=" + exp +
                ", hp=" + hp +
                ", mana=" + mana +
                ", damage=" + damage +
                '}';
    }

    @Override
    public void levelUp() {
        this.level++;
        this.hp += 50;
        this.mana += 10;
        this.s += 10;
        this.a += 30;
        this.i += 10;
        this.damage += 50;
    }

    @Override
    public void attack(Enemy enemy) {
        enemy.hp -= this.damage;      // archer attack
        if(enemy.hp <= 0){                      // if archer win
            if(this.exp + enemy.exp >= 500){    // if archer get new level
                this.exp = this.exp + enemy.exp;
                if(this.exp >= 500){
                    while (this.exp >= 500){
                        this.exp -= 500;
                        levelUp();
                    }
                }
                else
                    levelUp();
            }
            else
                this.exp += enemy.exp;
        }
        else{                                   // enemy attack
            Random random = new Random();
            int r = random.nextInt(100);
            if (r > 30) {                       // 30%
                this.hp -= enemy.damage;
            }
            if(this.hp <= 0){
                System.out.println("Ваш герой был убит");
            }
        }
    }

    @Override
    public void defense(Enemy enemy) {

    }
            /*
                класс должен обладать всеми свойствами героя при создании лучник должен обладать следующими характеристиками:
                здоровье - 200, мана - 50, сила - 20, ловкость - 150, интеллект - 30, опыт 0, урон - 200.
            атака по аналогии с воином, но в процессе атаки лучник имеет 30% шанс избежать урон
            лучник всегда открывает предмет - 100%
            Новый уровень дает +10 ко всем характеристикам, +50 здоровья и +50 урона и 30 ловкости.
            */
}

class Magician extends Hero{
    public Magician(){
        //базовые значение
        this.hp = 100;
        this.mana = 5000;
        this.s = 5;
        this.a = 30;
        this.i = 300;
        this.exp = 0;
        this.damage = 40;
    }
    private ArrayList<Item> casts = new ArrayList<>();

    public ArrayList<Item> getCasts() {
        return casts;
    }

    /*
            класс должен обладать всеми свойствами героя при создании маг должен обладать следующими характеристиками:
            здоровье - 100, мана - 5000, сила - 5, ловкость - 30, интеллект - 300, опыт 0, урон - 40.
        атака по аналогии с воином
        помимо обычной атаки, маг имеет возможность бить заклинанием в процессе атаки
        защита мага осуществляется магическим щитом, который полгащает весь урон, но вместо здоровья тратит ману. Новый уровень дает +10 ко всем характеристикам, +30 здоровья, 1000 маны и +10 урона.
        В случае возможности убить врага с руки - маг бьет с руки!!! в Первую очередь
        */
    public void makeCast(Enemy enemy){
        // если в списке есть заклятия можно его произнести потратив 100 маны и нанеся 1000 урона цели.
        // после произнесения предмет из списка пропадает
        if(!casts.isEmpty() && mana >= 100) {
            enemy.hp -= 1000;
            mana -= 100;
            casts.remove(casts.size()-1);
        }
    }

    public void eduCast(Item item){
        /* если предмет или предметы из списка имеют тип 2, это заклинания и их нужно добавить в список заклинаний мага */
            if(item.type == 2)
                casts.add(item);
    }

    @Override
    public void levelUp() {
        this.level++;
        this.hp += 30;
        this.mana += 1000;
        this.s += 10;
        this.a += 10;
        this.i += 10;
        this.damage += 10;
    }

    @Override
    public void attack(Enemy enemy) {

        if(enemy.hp <= this.damage) {
            enemy.hp -= this.damage;      // warrior attack
            if (enemy.hp <= 0) {                      // if warrior win
                if (this.exp + enemy.exp >= 500) {    // if warrior get new level
                    this.exp = this.exp + enemy.exp;
                    if (this.exp >= 500) {
                        while (this.exp >= 500) {
                            this.exp -= 500;
                            levelUp();
                        }
                    } else
                        levelUp();
                } else
                    this.exp += enemy.exp;
            } else {                                   // enemy attack
                this.hp -= enemy.damage;
                if (this.hp <= 0) {
                    System.out.println("Ваш герой был убит");
                }
            }
        }
        else{
            makeCast(enemy);
            if (enemy.hp <= 0) {                      // if warrior win
                if (this.exp + enemy.exp >= 500) {    // if warrior get new level
                    this.exp = this.exp + enemy.exp;
                    if (this.exp >= 500) {
                        while (this.exp >= 500) {
                            this.exp -= 500;
                            levelUp();
                        }
                    } else
                        levelUp();
                } else
                    this.exp += enemy.exp;
            } else {                                   // enemy attack
                this.hp -= enemy.damage;
                if (this.hp <= 0) {
                    System.out.println("Ваш герой был убит");
                }
            }
        }
    }

    @Override
    public void defense(Enemy enemy) {
        if (this.mana - enemy.damage >= 0) {
            this.mana -= enemy.damage;
        }
        else{
            this.hp -= enemy.damage + this.mana;
            if(this.hp <= 0){
                System.out.println("Ваш герой был убит");
            }
        }
    }

    @Override
    public String toString() {
        return "Magician{" +
                "nickName='" + nickName + '\'' +
                ", level=" + level +
                ", s=" + s +
                ", a=" + a +
                ", i=" + i +
                ", exp=" + exp +
                ", hp=" + hp +
                ", mana=" + mana +
                ", damage=" + damage +
                '}';
    }
}
