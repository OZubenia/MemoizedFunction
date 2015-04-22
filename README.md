# MemoizedFunction
Memoize function allows you to create functions that cache their results by args.  The next calls will look at cache at first, and if restult not found computation will be performed ;; and saved to the cache.   ;; The task is to write bounded-memoize function, wich will cache last N computations.  Returned function must be thread-safe.

Поскольку в Java нет функций, то для того чтобы ее создать использовал интерфейс Function<F, T>. 
Дальше создал статический клас(функцию) f_sqr, которую уже можно задавать как аргумент в другой.
Потом собственно реализовал саму MemoizedFunction. Для хранения результатов в кеш использовал LinkedHashMap, причем переопределил метод removeEldestEntry, указав макс количество хранимых результатов cacheLimit=5. Соответственно если будет добавлятся cacheLimit+1 результат, то первый будет удален.
Функция работает так: делаю поиск по аргументу в списке, если находит, то берет результат из памяти, если же нет, то выполняю функцию, добавляю результат в список.
