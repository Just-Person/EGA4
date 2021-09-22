import java.util.*;


public class Main {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество шагов у программы: ");
        int n = in.nextInt();
        Random rnd = new Random();
        int randomstart = Math.abs(rnd.nextInt()) % 128;
        MyString myString = new MyString(7,randomstart);
        System.out.println("Точка входа - "+ myString.getMaxX() + "------" + myString.geti(myString.getMaxX()));
        myString.printgrey();
        myString.myremovegrey(myString.getMaxX());
        myString.getHashMap().clear();
        myString.getHashMap().put(myString.getMaxX(),myString.getMax());
        System.out.println("Первоначальный максимум - "+ myString.getMaxX() + ", значение -  " + myString.getMax());
        int i = 0;
        String buffer = "";
        while (i < n)
        {
            System.out.println("Номер шага - "+ i);
            System.out.println("Текущий max - "+ myString.getMaxX() + ", значение - " + myString.getMax());
            myString.printlocal(myString.getMaxX());
            buffer = myString.getIndex();
            if (buffer!=null)
            {
                myString.setMax(myString.geti(buffer));
                myString.setMaxX(buffer);
                System.out.println("Новый максимум - " + myString.getMaxX() + ", значение - " + myString.getMax());
                myString.getHashMap().put(myString.getMaxX(),myString.getMax());
                myString.myremovegrey(myString.getMaxX());
                i++;
            }
            else {
                i = n + 1;
                System.out.println("Мы нашли локальный максимум!");
            }
        }
        System.out.println("Максимальная строчка - " + myString.getMaxX() + ", её значение - " + myString.getMax());
        System.out.print("Наш путь - ");
    myString.printhash();
    System.out.println();
    System.out.print("После перевода в бинарный код: ");
    System.out.println(myString.greyreverse(myString.getMaxX()) + "------"+myString.getMax());
    }

}
