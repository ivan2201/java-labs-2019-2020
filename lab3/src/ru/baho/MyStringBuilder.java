package ru.baho;

import java.util.ArrayDeque;
import java.lang.StringBuilder;

public class MyStringBuilder
{
  private StringBuilder strBuilder;

  private ArrayDeque< ReverseFunction > stack;

  MyStringBuilder()
  {
    stack = new ArrayDeque< ReverseFunction >();
    strBuilder = new StringBuilder();
  }

  interface ReverseFunction
  {
    public void reverse();
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
    final int length = strBuilder.length();
    strBuilder.append(str);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder append(StringBuffer sb)
  {
    final int length = strBuilder.length();
    strBuilder.append(sb);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }


  public MyStringBuilder append(CharSequence s)
  {
    final int length = strBuilder.length();
    strBuilder.append(s);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder append(CharSequence s, int start, int end)
  {
    final int length = strBuilder.length();
    strBuilder.append(s, start, end);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }


  public MyStringBuilder append(char[] str)
  {
    final int length = strBuilder.length();
    strBuilder.append(str);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder append(char[] str, int offset, int len)
  {
    final int length = strBuilder.length();
    strBuilder.append(str, offset, len);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }


  public MyStringBuilder append(boolean b)
  {
    final int length = strBuilder.length();
    strBuilder.append(b);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder append(char c)
  {
    final int length = strBuilder.length();
    strBuilder.append(c);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder append(int i)
  {
    final int length = strBuilder.length();
    strBuilder.append(i);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }


  public MyStringBuilder append(long lng)
  {
    final int length = strBuilder.length();
    strBuilder.append(lng);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }


  public MyStringBuilder append(float f)
  {
    final int length = strBuilder.length();
    strBuilder.append(f);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }


  public MyStringBuilder append(double d)
  {
    final int length = strBuilder.length();
    strBuilder.append(d);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder appendCodePoint(int codePoint)
  {
    final int length = strBuilder.length();
    strBuilder.appendCodePoint(codePoint);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(length, newLength);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder delete(int start, int end)
  {
    final int position = start;
    final String str = strBuilder.substring(start, end);
    strBuilder.delete(start, end);
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.insert(position,str);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder deleteCharAt(final int index)
  {
    final char ch = strBuilder.charAt(index);
    strBuilder.deleteCharAt(index);
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.insert(index, ch);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder replace(final int start, final int end, String str)
  {
    final String strOld = strBuilder.substring(start, end);
    strBuilder.replace(start, end, str);
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.replace(start, end, strOld);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int index, char[] str, int offset,
                                final int len)
  {
    strBuilder.insert(index, str, offset, len);
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(index, index + len);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, Object obj)
  {
    final int length = strBuilder.length();
    strBuilder.insert(offset, obj);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, newLength - length + offset);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, String str)
  {
    strBuilder.insert(offset, str);
    final int delta = str.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, offset + delta);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, char[] str)
  {
    strBuilder.insert(offset, str);
    final int delta = str.length;
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, offset + delta);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int dstOffset, CharSequence s)
  {
    strBuilder.insert(dstOffset, s);
    final int delta = s.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(dstOffset, dstOffset + delta);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int dstOffset, CharSequence s,
                                final int start, final int end)
  {
    strBuilder.insert(dstOffset, s, start, end);
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(dstOffset, dstOffset + end - start);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, boolean b)
  {
    final int length = strBuilder.length();
    strBuilder.insert(offset, b);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, newLength - length + offset);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, char c)
  {
    final int length = strBuilder.length();
    strBuilder.insert(offset, c);
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, offset + 1);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, int i)
  {
    final int length = strBuilder.length();
    strBuilder.insert(offset, i);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, newLength - length + offset);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, long l)
  {
    final int length = strBuilder.length();
    strBuilder.insert(offset, l);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, newLength - length + offset);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, float f)
  {
    final int length = strBuilder.length();
    strBuilder.insert(offset, f);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, newLength - length + offset);
                    }
                  }
    );
    return this;
  }

  public MyStringBuilder insert(final int offset, double d)
  {
    final int length = strBuilder.length();
    strBuilder.insert(offset, d);
    final int newLength = strBuilder.length();
    stack.addLast(new ReverseFunction()
                  {
                    @Override
                    public void reverse()
                    {
                      strBuilder.delete(offset, newLength - length + offset);
                    }
                  }
    );
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
    strBuilder.reverse();
    stack.push(new ReverseFunction()
    {
      @Override
      public void reverse()
      {
        strBuilder.reverse();
      }
    });
    return this;
  }

  public String toString()
  {
    return strBuilder.toString();
  }

  public MyStringBuilder undo()
  {
    stack.pollLast().reverse();
    return this;
  }
}
