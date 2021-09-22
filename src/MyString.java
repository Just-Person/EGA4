import java.util.HashMap;

public class MyString {
    private HashMap<String, Integer> hashMap = new HashMap<>();
    private HashMap<String, Integer> greyMap = new HashMap<>();
    private int startposition = 0;
    private int length = 0;
    private int max = 0;
    private String maxX = "";
    MyString (int length, int startposition)
    {
        this.length = length;
        for (int i = 0; i<mypow(2,length); i++)
        {
            this.hashMap.put(dectotwo(i,length),mypow(i - mypow(2,length-1),2));
        }
        for (int i = 0; i<mypow(2,length);i++)
        {
            this.greyMap.put(grey(dectotwo(i,length),1),mypow(i - mypow(2,length-1),2));
        }
        this.startposition = startposition;
        this.maxX = grey(dectotwo(startposition,length),1);
        this.max = this.greyMap.get(maxX);
    }
    public String plus (String s1, String s2)
    {
        String s = "";
        for (int i = 0; i<s1.length();i++)
        {
            if ((s1.charAt(i)=='1' && s2.charAt(i)=='0')
            || (s1.charAt(i)=='0' && s2.charAt(i)=='1')) s +="1";
            else s +="0";
        }
        return s;
    }

    public String grey (String s1, int index)
    {
        String s = "";
        for (int i = 0; i<index; i++)
            s +="0";
        s+=s1.substring(0,s1.length()-index);
        s = plus(s,s1);
        return s;
    }
    public String getIndex ()
    {
        String buffer = "";
        int max = 0;
        String maxS = "";
        for (int i = 0; i<this.length; i++)
        {
            buffer = changeIndexString(maxX,i);
            if (geti(buffer)!=null) {
                if (geti(buffer) > max)
                {
                    max = geti(buffer);
                    maxS = buffer;
                }
            }
        }
        if (max>this.max)
        {
           return maxS;
        } else
        return null;
    }
    public String changeIndexString(String s, int pos)
    {
        String s1 = "";
        try
        {
            s.charAt(pos);
        } catch (Exception e)
        {
            System.out.println("Вышли за границу HashMap");
            pos = -1;
        }
        if (pos>=0) {
            for (int i = 0; i < pos; i++)
                s1 += s.charAt(i);
            if (s.charAt(pos) == '1') s1 += '0';
            else s1 += '1';
            if (pos < s.length() - 1)
                s1 += s.substring(pos + 1);
        }
        return s1;
    }
    public  int twotodec(String s)
    {
        int result = 0;
        for (int i = 0; i<s.length(); i++)
        {
            result +=Integer.parseInt(String.valueOf(s.charAt(i)))*mypow(2,s.length()-i-1);
        }
        return result;
    }
    public  String dectotwo(int dec,int num)
    {
        String result = "";
        while (dec > 0)
        {
            result+=String.valueOf(dec % 2);
            dec /=2;
        }
        while (result.length()<this.length)
        {
            result += "0";
        }
        String buffer = "";
        for (int i = 0; i<result.length(); i++)
            buffer += result.charAt(result.length() - i - 1);
        return buffer;
    }
    public  int mypow(int first, int second)
    {
        int buffer = first;
        if (second == 0) return 1;
        else {
            for (int i = 0; i < second - 1; i++) {
                first = first * buffer;
            }
            return first;
        }
    }
    public void printlocal(String num)
    {
        System.out.print("Текущая окрестность: ");
        String buffer = "";
        int k = 0;
        for (int i = 0; i<this.length; i++)
        {
            buffer = changeIndexString(num,i);
            if (geti(buffer)!=null) {
                System.out.print(buffer+"  ");
                k = 1;
            }
        }
        if (k==1)
            System.out.println();
    }
    public Integer geti(String num)
    {
        if (greyMap.get(num)!=null)
            return greyMap.get(num);
        else return null;
    }

    public HashMap<String, Integer> getGreyMap() {
        return greyMap;
    }

    public void setGreyMap(HashMap<String, Integer> greyMap) {
        this.greyMap = greyMap;
    }

    public void myremovegrey (String del)
    {
        if (greyMap.get(del) != null)
        {
            greyMap.remove(del);
        }
    }
    public void myremovehash (String del)
    {
        if (hashMap.get(del) != null)
        {
            hashMap.remove(del);
        }
    }
    public int size()
    {
        return hashMap.size();
    }
    public void printgrey() {
        for (int i = 0; i < greyMap.size(); i++) {
            if (greyMap.get(grey(dectotwo(i,7),1)) != null)
                System.out.println(grey(dectotwo(i,length),1) + "------"
                        + greyMap.get(grey(dectotwo(i,length),1)));
        }
    }
    public void printhash(){
        int min = 10000;
        String index = "";
        for (int j = 0; j < mypow(2, this.length); j++) {
            min = 10000;
            for (int i = 0; i < mypow(2, this.length); i++) {
                if (hashMap.get(grey(dectotwo(i, this.length), 1)) != null
                        && hashMap.get(grey(dectotwo(i, this.length), 1)) < min) {
                    min = hashMap.get(grey(dectotwo(i, this.length), 1));
                    index = grey(dectotwo(i, this.length), 1);
                }
            }
            if (hashMap.get(index) != null) {
                System.out.print(index + "======"
                        + min + "   ");
                hashMap.remove(index);
            }
        }
    }
    public String greyreverse(String str)
    {
        HashMap <Integer,String> buffer = new HashMap<>();
        buffer.put(0,str);
        for (int i = 1; i<this.length; i++)
        {
            str = plus(str,grey(str,1));
            buffer.put(i,str);
        }
        str = buffer.get(0);
        for (int i = 1; i<this.length; i++)
        {
            str = plus(str,buffer.get(i));
        }
        return str;
    }
    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Integer> hashMap) {
        this.hashMap = hashMap;
    }

    public int getStartposition() {
        return startposition;
    }

    public void setStartposition(int startposition) {
        this.startposition = startposition;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getMaxX() {
        return maxX;
    }

    public void setMaxX(String maxX) {
        this.maxX = maxX;
    }
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}