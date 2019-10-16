package ru.baho;

import java.util.ArrayDeque;
import java.lang.StringBuilder;

public class MyStringBuilder
{
  private StringBuilder strBuilder;
  ArrayDeque<String> stack;

  MyStringBuilder()
  {
    stack = new ArrayDeque<String>();
    strBuilder = new StringBuilder();
  }

  public int compareTo(MyStringBuilder another)
  {
    return strBuilder.toString().compareTo(another.toString());
  }


  public MyStringBuilder append(Object obj)
  {
    return append(String.valueOf(obj));
  }

  public MyStringBuilder append(String str)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(str);
    return this;
  }

  public MyStringBuilder append(StringBuffer sb)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(sb);
    return this;
  }


  public MyStringBuilder append(CharSequence s)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(s);
    return this;
  }

  public MyStringBuilder append(CharSequence s, int start, int end)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(s, start, end);
    return this;
  }


  public MyStringBuilder append(char[] str)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(str);
    return this;
  }

  public MyStringBuilder append(char[] str, int offset, int len)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(str, offset, len);
    return this;
  }


  public MyStringBuilder append(boolean b)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(b);
    return this;
  }

  public MyStringBuilder append(char c)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(c);
    return this;
  }

  public MyStringBuilder append(int i)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(i);
    return this;
  }


  public MyStringBuilder append(long lng)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(lng);
    return this;
  }


  public MyStringBuilder append(float f)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(f);
    return this;
  }


  public MyStringBuilder append(double d)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.append(d);
    return this;
  }

  public MyStringBuilder appendCodePoint(int codePoint)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.appendCodePoint(codePoint);
    return this;
  }

  public MyStringBuilder delete(int start, int end)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.delete(start, end);
    return this;
  }

  public MyStringBuilder deleteCharAt(int index)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.deleteCharAt(index);
    return this;
  }

  public MyStringBuilder replace(int start, int end, String str)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.replace(start, end, str);
    return this;
  }

  public MyStringBuilder insert(int index, char[] str, int offset,
                                int len)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(index, str, offset, len);
    return this;
  }

  public MyStringBuilder insert(int offset, Object obj)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, obj);
    return this;
  }

  public MyStringBuilder insert(int offset, String str)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, str);
    return this;
  }

  public MyStringBuilder insert(int offset, char[] str)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, str);
    return this;
  }

  public MyStringBuilder insert(int dstOffset, CharSequence s)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(dstOffset, s);
    return this;
  }

  public MyStringBuilder insert(int dstOffset, CharSequence s,
                                int start, int end)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(dstOffset, s, start, end);
    return this;
  }

  public MyStringBuilder insert(int offset, boolean b)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, b);
    return this;
  }

  public MyStringBuilder insert(int offset, char c)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, c);
    return this;
  }

  public MyStringBuilder insert(int offset, int i)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, i);
    return this;
  }

  public MyStringBuilder insert(int offset, long l)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, l);
    return this;
  }

  public MyStringBuilder insert(int offset, float f)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, f);
    return this;
  }

  public MyStringBuilder insert(int offset, double d)
  {
    stack.addLast(strBuilder.toString());
    strBuilder.insert(offset, d);
    return this;
  }


  public int indexOf(String str)
  {
    return strBuilder.indexOf(str);
  }


  public int indexOf(String str, int fromIndex)
  {
    return strBuilder.indexOf(str, fromIndex);
  }


  public int lastIndexOf(String str)
  {
    return strBuilder.lastIndexOf(str);
  }


  public int lastIndexOf(String str, int fromIndex)
  {
    return strBuilder.lastIndexOf(str, fromIndex);
  }


  public MyStringBuilder reverse()
  {
    stack.addLast(strBuilder.toString());
    strBuilder.reverse();
    return this;
  }

  public String toString()
  {
    return strBuilder.toString();
  }

  public MyStringBuilder undo()
  {
    strBuilder = new StringBuilder(stack.pollLast());
    return this;
  }
}
