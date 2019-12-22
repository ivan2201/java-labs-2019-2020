package ru.baho;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Properties implements Map<String,String>
{
  Map< String, String> properties;
  private Properties()
  {
    properties = new HashMap<String, String>();
  }

  Properties(File file) throws IOException
  {
    if (file == null) throw new NullPointerException("fail is null");
    properties = new HashMap<String, String>();
    if (file.exists())
    {
      Scanner sc = new Scanner(file);
      while (sc.hasNext())
      {
        String str = sc.next();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        boolean openScopes = false;
        for (i = 0; i < str.length(); i++)
        {
          char c = str.charAt(i);
          if (c == ' ' || c == '\t' || c == '\r') continue;
          if (c == '=') break;
          builder.append(c);
        }
        if (str.charAt(i) != '=') continue;
        String key = builder.toString();
        builder = new StringBuilder();
        for (++i; i < str.length(); i++)
        {
          char c = str.charAt(i);
          if ((c == ' ' || c == '\t' || c == '\r') && !openScopes) continue;
          if (c == '"' || c == '\'')
            if (openScopes) openScopes = false;
            else openScopes = true;
            builder.append(c);
        }
        properties.put(key, builder.toString());
      }
    }
  }

  boolean good = true;
  void saveFile(File file) throws IOException {
    good = true;
    FileWriter writer = new FileWriter(file);
    properties.forEach((key, value) -> {
      try
      {
        writer.write(key + "=" + value + "\n");
      }
      catch (IOException ex)
      {
        good = false;
      }
    });
    if (!good)
    {
      writer.close();
      throw  new IOException("error: can't save file");
    }
    writer.flush();
    writer.close();
  }

  @Override
  public int size()
  {
    return properties.size();
  }

  @Override
  public boolean isEmpty()
  {
    return properties.isEmpty();
  }

  @Override
  public boolean containsKey(Object key)
  {
    return properties.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value)
  {
    return containsValue(value);
  }

  @Override
  public String get(Object key)
  {
    return properties.get(key);
  }

  @Override
  public String put(String key, String value)
  {
    return properties.put(key, value);
  }

  @Override
  public String remove(Object key)
  {
    return properties.remove(key);
  }

  @Override
  public void putAll(Map<? extends String, ? extends String> m)
  {
    properties.putAll(m);
  }

  @Override
  public void clear()
  {
    properties.clear();
  }

  @Override
  public Set<String> keySet()
  {
    return properties.keySet();
  }

  @Override
  public Collection<String> values()
  {
    return properties.values();
  }

  @Override
  public Set<Entry<String, String>> entrySet()
  {
    return properties.entrySet();
  }
}
