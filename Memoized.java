import java.util.*;
/**
* Эта программа демонстрирует кеширование функции
* @version 1.00 2015/04/20
* @author OZubenia
*/
public class Memoized
{
public static void main(String[] args) 
{   
System.out.println( MemoizedFunction(f_sqr, 25) );
System.out.println(MemoizedFunction(f_sqr, 25) );
System.out.println(MemoizedFunction(f_sqr, 3) );
System.out.println(MemoizedFunction(f_sqr, 25) );
System.out.println(MemoizedFunction(f_sqr, 5) );
System.out.println(MemoizedFunction(f_sqr, 3) );
System.out.println(MemoizedFunction(f_sqr, 2) );
System.out.println(MemoizedFunction(f_sqr, 10) );
System.out.println(MemoizedFunction(f_sqr, 10) );
System.out.println(MemoizedFunction(f_sqr, 12) );
System.out.println(MemoizedFunction(f_sqr, 13) );
System.out.println( MemoizedFunction(f_sqr, 25) );
}

/*для использования функционального программирования необходимо реализовать данный интерфейс*/
public interface Function<F, T> 
	{
	 T apply(F from);
	}
;
		//анонимную реализацию этого интерфейса - «анонимная функция»:
				public static final Function<Integer, Integer> f_sqr = new Function<Integer, Integer>() 
						{   @Override 
							public Integer apply(Integer from) 
							{
								return from*from;
							}
						}
				;
		//другая «анонимная функция»:
				public static final Function<Integer, Integer> f_qbe= new Function<Integer, Integer>()
				{
							@Override 
							public Integer apply(Integer from) 
							{
								return from*from*from;
							}
						}
				;
//Создаю хранилице для кеширования результатов
public static Map<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>()
	{
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) //кэш удерживается в максимальном размере в cacheLimit элементов
		{
		return size() > cacheLimit; 
		}
	};
	
//Функция для мемоизации результатов
public static Integer MemoizedFunction(Function<Integer, Integer>  myFunc, Integer x)
{
String res_source=null;
Integer result = null;
synchronized( cache ) 
	{
       result = cache.get(x);
	   res_source = "   ,cache hit ";
       if ( result==null && ! cache.containsKey(x) ) 
	   {
           result = myFunc.apply(x);
		   cache.put(x,result);
		   res_source = "   ,cache miss, save to cache ";
       }
   };
   
System.out.println("-----------------------------------------------");
System.out.println("f(" + x + ")="+res_source); 
return result;
}

final public static int cacheLimit = 5; //количество запоминаний в кеш
}

