package com.programming.techie.resilience4j;

class Service implements RemoteService {
    @Override
    public int process(int i) {
        System.out.println("Service Process: " + i);

        if( i > 17 ) {
            try {
                Thread.sleep(4000);
            } catch (Exception ex) {

            }
        }

//        if (i   == 7){
//            throw new RuntimeException("Service failed");
//        }
        if (Math.random() > 0.5) {
            System.out.println("exception raised " + i);
            throw new RuntimeException("External service failed");
        }
        return i;
    }
}
