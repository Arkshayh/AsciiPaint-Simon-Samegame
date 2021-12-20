**TD lambda**

Q1 :

    1. Pas accepter 
    2. Pas accepter
    3. Accepter
    4. Accepter
    5. Pas accepter
    6. Pas accepter
    7. Accepter
    8. Pas accepter
    9. Pas accepter
    10. Accepter
    11. Accepter
    12. Accepter

Q2 :
    
    1. IntPredicate predicate = (x) ->
        {
            if (x == 544331)
                return true;
            return false;
        };

    2.  BiPredicate<String, Integer> filter = (x, y) -> {
            return x.length() == y;
        };

    3.  DoubleConsumer d = (x) -> System.out.println(x*x);

    4.  ToIntFunction<Integer> x = value -> value * 25;

    5.  DoubleFunction<Double> x = value -> value * 5;

    6. IntToDoubleFunction i = (x) -> {return Math.sin(x);};
