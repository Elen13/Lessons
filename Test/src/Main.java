import javafx.beans.property.MapProperty;

import java.math.BigInteger;
import java.util.*;
import java.util.Scanner;

public class Main {

    static int c = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

//        for (int i =0; i < 100; i++) {
//            Random r = new Random();
//            System.out.print(r.nextInt(7) + 1 + " ");
//        }
//        BigInteger a = new BigInteger(scanner.next());
//        BigInteger b = new BigInteger(scanner.next());

//        int n = scanner.nextInt();
//        int a[] = new int[n];
//        for(int i = 0; i < n; i++){
//            a[i] = scanner.nextInt();
//        }

//        String[] sW = scanner.nextLine().split(" ");
//        String s = scanner.nextLine();
//        Long n = scanner.nextLong();

//        ParametrList<Integer> elI = new ParametrList<>();
//        elI.add(0);
//        elI.add(1);
//        elI.add(2);
//        elI.add(3);
//        elI.add(4);
//        elI.add(5);
//        elI.add(6);
//        elI.add(7);
//        elI.set(3,555);
//        System.out.println(elI);
//        elI.insert(5,777);
//        System.out.println("find " + elI.find(9));
//        System.out.println(elI);
//        elI.remove(2);
//        System.out.println("get: " + elI.get(2));
//        System.out.println(elI);

        ParametrList<String> elS = new ParametrList<>();
        elS.add("0");
        elS.add("1");
        elS.add("2");
        elS.add("3");
        elS.add("4");
        elS.add("5");
        elS.add("6");
        elS.add("7");
        elS.set(3,"555");
        System.out.println(elS);
        elS.insert(5,"777");
        System.out.println("find " + elS.find("7"));
        System.out.println(elS);
        elS.remove(2);
        String s = elS.get(2);
        System.out.println("get: " + elS.get(2));
        System.out.println(elS);


//        examStatistic(scanner);
//        sortStudent(scanner);
//        sortNodes(scanner);
//        testMyRPG();
//        testMyStack(scanner);
//        addToMyArray(scanner);
//        shopList(scanner);
//        friends(scanner);
//        competitions(scanner);
//        System.out.println(fib(n));
//        System.out.println(NOD(a, b));
//        System.out.println(a.multiply(b));
//        foo(n);
//        System.out.println(c);
//        System.out.println(isPrime(n));
//        System.out.println(isPalindrome(s));
//        System.out.println(maxLengthWord(sW));
//        printN(7);
//        sort(a);

//        roundTabel(scanner);
//        hardPolindrom(scanner);

    }

    static class School {
        int number;
        ArrayList<StudentEx> students = new ArrayList<>();
        double avgMark = 0;
        double avgMath = 0;
        double avgRus = 0;
        double avgInf = 0;

        public School(int number) {
            this.number = number;
        }
    }

    static class StudentEx {
        String firstName;
        String secondName;
        int school;
        ArrayList<Integer> mark;        // 0 -- math; 1 -- rus; 2 -- inf;

        public StudentEx(String first, String second, int school) {
            this.firstName = first;
            this.secondName = second;
            this.school = school;
            this.mark = new ArrayList<>(2);
        }
        public void addMark(int m) { mark.add(m); }

        @Override
        public String toString() {
            return "StudentEx{" +
                    "firstName='" + firstName + '\'' +
                    ", secondName='" + secondName + '\'' +
                    ", school=" + school +
                    ", mark=" + mark +
                    '}';
        }
    }

    static void examStatistic(Scanner scanner){
        int l = scanner.nextInt();
        int countSchool = 0;
        ArrayList<Integer> c = new ArrayList<>(l);
        StudentEx [] students = new StudentEx[l];

        for (int i = 0; i < l; i++) {
            StudentEx student = new StudentEx(scanner.next(), scanner.next(), scanner.nextInt());
            student.addMark(scanner.nextInt());
            student.addMark(scanner.nextInt());
            student.addMark(scanner.nextInt());
            students[i] = student;
            if(!c.contains(student.school)){
                c.add(student.school);
                countSchool++;
            }
        }

        c.clear();
        School [] schools = new School[countSchool];

        for(StudentEx el : students){
            for (int i = 0; i < countSchool; i++) {
                if(schools[i] != null && el.school == schools[i].number){
                    schools[i].students.add(el);
                    break;
                }
                else{
                    if(schools[i] == null){
                        schools[i] = new School(el.school);
                        schools[i].students.add(el);
                        break;
                    }
                }
            }
        }

        for(School sc : schools){
            for(StudentEx student : sc.students){
                sc.avgMath += student.mark.get(0);
                sc.avgRus += student.mark.get(1);
                sc.avgInf += student.mark.get(2);
                sc.avgMark += student.mark.get(0) + student.mark.get(1) + student.mark.get(2);
            }
            sc.avgMath = sc.avgMath / sc.students.size();
            sc.avgRus = sc.avgRus / sc.students.size();
            sc.avgInf = sc.avgInf / sc.students.size();
            sc.avgMark = (sc.avgMath + sc.avgRus + sc.avgInf) / 3;
        }

        double avMath = 0;
        double avRus = 0;
        double avInf = 0;
        for(StudentEx el : students){
            avInf += el.mark.get(2);
            avMath += el.mark.get(0);
            avRus += el.mark.get(1);
        }

        System.out.printf(Locale.ROOT,"Отчет по городу: математика - %.1f, русский язык - %.1f, инфрматика - %.1f, общий средний балл - %.1f\n",
                avMath/l, avRus/l, avInf/l, (avMath/l + avRus/l + avInf/l)/3);

        System.out.println("Отчет по школам:");
        Comparator<School> comparator = Comparator.comparing(o -> o.avgMark);
        Arrays.sort(schools,comparator.thenComparing(o -> o.avgMath).thenComparing(o -> o.avgRus).thenComparing(o -> o.avgInf).thenComparing(o -> o.number).reversed());
        for(School sc : schools){
            System.out.printf(Locale.ROOT,"Школа № %d: математика - %.1f, русский язык - %.1f, инфрматика - %.1f, общий средний балл - %.1f\n",
                    sc.number, sc.avgMath, sc.avgRus, sc.avgInf, sc.avgMark);
        }


        Comparator<StudentEx> comparator1 = Comparator.comparing(o -> o.mark.get(0));
        Arrays.sort(students, comparator1.reversed().thenComparing(o -> o.firstName).thenComparing(o -> o.secondName));
//        for(StudentEx el : students){
//            System.out.printf("Результаты %s \t %d \t %d \t %d\n", el.firstName, el.mark.get(0), el.mark.get(1), el.mark.get(2));
//        }
        for(int i = 0; i < l; i++) {
            if (students[0].mark.get(0) == students[i].mark.get(0))
                System.out.printf(Locale.ROOT, "Лучший результат по математике - %s %s - %d\n", students[i].firstName, students[i].secondName, students[i].mark.get(0));
        }
        comparator1 = Comparator.comparing(o -> o.mark.get(1));
        Arrays.sort(students, comparator1.reversed().thenComparing(o -> o.firstName).thenComparing(o -> o.secondName));
        for(int i = 0; i < l; i++) {
            if (students[0].mark.get(1) == students[i].mark.get(1))
                System.out.printf(Locale.ROOT, "Лучший результат по русскому языку - %s %s - %d\n", students[i].firstName, students[i].secondName, students[i].mark.get(1));
        }
        comparator1 = Comparator.comparing(o -> o.mark.get(2));
        Arrays.sort(students, comparator1.reversed().thenComparing(o -> o.firstName).thenComparing(o -> o.secondName));
        for(int i = 0; i < l; i++) {
            if (students[0].mark.get(2) == students[i].mark.get(2))
                System.out.printf(Locale.ROOT, "Лучший результат по информатике - %s %s - %d\n", students[i].firstName, students[i].secondName, students[i].mark.get(2));
        }

    }
    /**Класс ученик имеет поля имя и оценка. Программа получает на вход число N, далее следует N строк с данными в формате:
     Имя оценка
     Необходимо отсортировать данный список учеников по убыванию оценок. В случае равных оценок учеников необходимо отсортировать по возрастанию имени.*/

    static class Student {
        String name;
        int mark;

        public Student(String name, int mark) {
            this.name = name;
            this.mark = mark;
        }
    }

    static void sortStudent(Scanner scanner){
        int l = scanner.nextInt();
        Student [] students = new Student[l];

        for (int i = 0; i < l; i++) {
            students[i] = new Student(scanner.next(), scanner.nextInt());
        }
        Comparator<Student> comparator = Comparator.comparing(obj -> obj.mark);
        Arrays.sort(students, comparator.reversed().thenComparing(obj -> obj.name));
        for(Student el : students)
            System.out.printf("%s %d\n", el.name, el.mark);

    }


    /**Программа получает на вход целое число N, далее следуют N пар значений x, y - координаты текущей точки.
     * Необходимо выстроить все точки по увеличению их удаленности от начала координат и вывести отсортированный спиcок точек на экран в формате :
     (1; 2)
     (2: 7)*/

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

   static void sortNodes(Scanner scanner){
        int l = scanner.nextInt();
       Node [] nodes = new Node[l];
       for (int i = 0; i < l; i++) {
           nodes[i] = new Node(scanner.nextInt(), scanner.nextInt());
       }
       Arrays.sort(nodes, Comparator.comparing(o -> Math.sqrt(Math.pow(o.x, 2) + Math.pow(o.y, 2))));
       for(Node el : nodes)
           System.out.printf("(%d, %d)\n", el.x, el.y);
   }

    static void testMyRPG(){
        ArrayList<Hero> myHeros = new ArrayList<>();
        myHeros.add(new Warrior());
        myHeros.add(new Archer());
        myHeros.add(new Magician());

        for(Hero hero : myHeros){

            //найден клад
            for (int i = 0; i < 300; i++){
                Item item = new Item(0, 0, i % 3); // i%3 - будет задавать предмету тип 0, 1, 2
                hero.openItem(item);  // герои получает предмет в руки

                if(hero instanceof Magician)
                    ((Magician) hero).eduCast(item);
            }
            System.out.println("Всего собрано: " + hero.items.size());

            ArrayList<Enemy> enemies = new ArrayList<>();
            enemies.add(new Enemy(30, 20, 1200));           // 1 - Пряничный человек
            enemies.add(new Enemy(2018, 1, 350));           // 2 - Туча комаров
            enemies.add(new Enemy(280, 50, 3050));          // 3 - Вор
            enemies.add(new Enemy(1950, 400, 5300));       // 4 - Страж
            enemies.add(new Enemy(100500, 100500, 100500)); // 5 - Гальватрон

            // fight
            int count  = 1;
            for(Enemy enemy : enemies){
                System.out.println( count++ + "-й бой:");
                System.out.println("    " + hero.toString());
                if(hero instanceof Magician)
                    System.out.println("@@@@@@@@@@@@ Cast magic @@@@@@@@@ ->>>> " + ((Magician) hero).getCasts().size());
                System.out.println("    " + enemy.toString());
                int counter = 1;
                while (hero.hp > 0 & enemy.hp > 0){
                    System.out.println( counter++ + " раунд:");
                    hero.attack(enemy);
                    hero.defense(enemy);
                    System.out.println("    Герои { hp=" + hero.hp + " mana=" + hero.mana + " }");
                    System.out.println("    Враг { hp=" + enemy.hp + " }");
                }
                System.out.println(hero.hp > 0 ? "Победа, герой получил опыт "+ enemy.exp : "Поражение");
                System.out.println();
            }
            System.out.println("    " + hero.toString() + "\n");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    static void testMyStack(Scanner scanner){
        MyStack inst = new MyStack();

        while(scanner.hasNextInt()) {
            System.out.println(inst.isEmpty());
            inst.push(scanner.nextInt());
            System.out.println(inst.getMax());
            System.out.println(inst.getMin());
            System.out.println(inst.isEmpty());
            System.out.println(inst.size());
            System.out.println(inst.pop());
            inst.printData();
        }
    }

    static void addToMyArray(Scanner scanner){
        MyArray inst = new MyArray();

        while(scanner.hasNextInt()) {
            inst.add(scanner.nextInt());
            System.out.println(inst.size);
            for (int elem : inst.data)
                System.out.print(elem + " ");
            System.out.println();
        }
    }
    /** Программа получает на вход строки в следующем формате: Фамилия пробел Имя пробел Наименование товара пробел сумма.
     Система формирует отчет за месяц, в котором информация обо всех покупателях и обо всех покупках для каждого покупателя.
     Покупатели отсортированы по алфавиту, покупки отсортированы по наименованию по алфавиту, в пределах конкретного покупателя.
     Внимательно посмотрите на примеры! Если не понятно - оставляйте свои вопросы в комментариях.
     На выводе, перед каждым продуктом ставится 4 пробела! */

    static void shopList(Scanner scanner){
        TreeMap<String, TreeSet<String>> map = new TreeMap<>();

        while(scanner.hasNext()){
            String data [] = scanner.nextLine().split(" ");
            if(map.containsKey(data[0] + " " + data[1])){
                map.get(data[0] + " " + data[1]).add(data[2] + " " + data[3]);
            }
            else{
                TreeSet<String> set = new TreeSet<>();
                set.add(data[2] + " " + data[3]);
                map.put(data[0] + " " + data[1], set);
            }
        }

        for(String name : map.keySet()){
            System.out.println(name + ":");
            for(String items : map.get(name)){
                System.out.println("    " + items);
            }
        }

    }

    /**Вводится несколько строк по два слова в каждой - имена двух друзей. Дружба - вещь взаимная(с двух сторон!!!). Иначе люди - не друзья.
     Необходимо вывести ответ в соответствии с примерами ниже - список друзей.
     Список состоит из имен людей, список выстроен в лексографическом порядке(по алфавиту) по возрастанию,
     после имени человека идет фраза " дружит с : ", далее следует список имен его друзей, выстроенный так же по возрастанию.*/

    static void friends(Scanner scanner){
        TreeMap<String, TreeSet<String>> map = new TreeMap();

        while (scanner.hasNext()){
            String s [] = scanner.nextLine().split(" ");

            if(map.containsKey(s[0])){
                map.get(s[0]).add(s[1]);
            }
            else {
                TreeSet<String> set = new TreeSet<>();
                set.add(s[1]);
                map.put(s[0], set);
            }

            if(map.containsKey(s[1])){
                map.get(s[1]).add(s[0]);
            }
            else {
                TreeSet<String> set = new TreeSet<>();
                set.add(s[0]);
                map.put(s[1], set);
            }
        }

        for(String person : map.keySet()){
            System.out.print(person + " дружит с : ");
            for(String friends : map.get(person)){
                System.out.print(friends + " ");
            }
            System.out.println();
        }
    }

    /** Программа получает на вход N целых чисел - штрафные баллы i-ого участника и К - мощность множества (количество элементов в нем).
     Необходимо выбрать К - элементное множество из уникальных по величине штрафных баллов.
     Необходимо вывести множество с минимальной суммой в порядке возрастания штрафных баллов через пробел или -1, если такое множество получить нельзя.*/

    static void competitions(Scanner scanner){
        int l = scanner.nextInt();;
        int k = scanner.nextInt();
        TreeSet set = new TreeSet();

        for(int i = 0; i < l; i++){
            set.add(scanner.nextInt());
        }

        if(set.size() < k) {
            System.out.println(-1);
        }
        else {
            for (int i = 0; i < k; i++) {
                if(!set.isEmpty())
                    System.out.print(set.pollFirst() + " ");
                else {
                    System.out.println(-1);
                    break;
                }

            }
        }
        System.out.println();
    }
    /** Числа Фибоначчи
     Каждое новое число является суммой двух предыдущих чисел. При чем, первое и второе числа Фибоначчи -  равны 1.
     Программа получает на вход натуральное число N, при чем N не превосходит 105
     Программа должна вывести N-ое по порядку число Фибоначчи.*/

    static BigInteger fib(int n){
        BigInteger f1 = BigInteger.ZERO;
        BigInteger f2 = BigInteger.ONE;
        BigInteger sum = BigInteger.ONE;

        for(int i = 2; i < n; i++){
            f1 = f2;
            f2 = sum;
            sum = f1.add(f2);
        }

        return sum;
    }

    static BigInteger NOD(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO))
            return a;
        return NOD(b, a.mod(b));

//        BigInteger t;
//        while (!b.equals(BigInteger.ZERO)) {
//            t = b;
//            b = a.mod(b);
//            a = t;
//        }
//        return a;
    }

    static int foo(int n){
        c++;
        return n < 2 ? 1 : foo(n-1) + foo(n-2);
    }

    static boolean isPrime(long n){
            // проверить, делится ли число без остатка
            for (int j=2; j <= n/j; j++)
                // если число делится без остатка, значит, оно не простое
                if((n%j) == 0) return false;
        return true;
    }

    /**Палиндромом называется такая строка, которая читается в обе стороны одинаково. Пробелы, регистр и знаки препинания не влияют на палиндромность.

     Например А роза,,,,упала На ЛАПУ А з о р а - палиндром,   а роза упала на лапу азору - не палиндром */

    public static boolean isPalindrome(String s){
        String sChar = "";

        for(char ch : s.toCharArray()){
            if(Character.isLetter(ch))
                sChar = sChar + ch;
        }
        StringBuilder sb = new StringBuilder(sChar).reverse();

        return sb.toString().toLowerCase().equals(sChar.toLowerCase());
    }

    /** Функция, возвращающая самое длинное слово из переданного массива. Если таких слов много - необходимо вернуть наименьшее в лексографическом порядке. */

    public static String maxLengthWord(String [] s){
        String res = "";
        int len = 0;

        Arrays.sort(s);
        for(String el : s){
            if(el.length() > len){
                res = el;
                len = el.length();
            }
        }

        return res;
    }

    public static void sort(int [] a){

        for(int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        for (int el: a) {
            System.out.print(el);
        }
    }

    /** 4 --> 1223334444 */
    public static void printN(int n){
        for (int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++)
                System.out.print(i);
        }
        System.out.println();
    }

    /**Дано слово состоящее из букв в любом регистре. Необходимо вывести на экран таблицу в соответствии с примером:

     Пусть слово = "abcde", тогда таблица должна быть вот такой -

     a b c d e
     b 0 0 0 d
     c 0 0 0 c
     d 0 0 0 b
     e d c b a

     Где пустое пространство заполняется нулями!
     Расстояние между буквами на выводе - 1 пробел */

    public static void roundTabel(Scanner scanner){
        String s = scanner.next();
        ArrayList<Character> arr = new ArrayList();

        for(int i = 0; i < s.length(); i++){
            arr.add(s.charAt(i));
        }

        for(int i = 0; i < arr.size(); i++) {
            if (i == arr.size() - 1)
                Collections.reverse(arr);

            for (int j = 0; j < arr.size(); j++) {

                if (i != arr.size() - 1 && j == 0) {
                    System.out.print(arr.get(i) + " ");
                } else if (i > 0 && j > 0 && i < arr.size() - 1 && j < arr.size() - 1) {
                    System.out.print("0 ");
                } else if (i != arr.size() - 1 && j == arr.size() - 1) {
                    System.out.print(arr.get(arr.size() - 1 - i) + " ");
                } else {
                    System.out.print(arr.get(j) + " ");
                }
            }

            System.out.println();
        }
    }

    /**Дана строка. Из данной строки необходимо сделать палиндром следующим образом:
     Необходимо сделать из данной строки палиндром минимально возможной длины.

     Например: abcb -> abcba, aaa -> aaa, a -> a, abcded -> abcdedcba */

    public static void hardPolindrom(Scanner scanner){
        String s = scanner.next();
        ArrayList<Character> arr = new ArrayList();
        ArrayList<Character> rev;

        for(int i = 0; i < s.length(); i++){
            arr.add(s.charAt(i));
        }

        rev = (ArrayList<Character>) arr.clone();

        for(int i = arr.size()-1; i >= 0; i--){
            ArrayList<Character> ar;
            ar =  (ArrayList<Character>) rev.clone();
            Collections.reverse(ar);
            if(rev.toString().equals(ar.toString())){
                break;
            }
            else {
                while (rev.size() > arr.size()){
                    rev.remove(rev.size()-1);
                }
            }
            for(int j = arr.size() - 1 - i; j >= 0; j-- ){
                rev.add(arr.get(j));
            }

        }
        String res = "";
        for (char ch : rev)
        {
            res += ch;
        }
        System.out.println(res);
    }
}
