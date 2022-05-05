package com.john.effective.chapter.two;

import java.util.Set;

public class SingletonPattern {
    public static void main(String[] args) {
        Set.of(John.INSTANCE, Thor.getInstance(), People.NENIR, People.NURR, People.LOM).forEach(Greeter::greet);
    }

    interface Greeter {
        void greet();
    }

    // Accessed via public property/field.
    private static class John
            implements Greeter {
        public static final John INSTANCE = new John();

        private John() {
            // No one else can make me!
        }

        public void greet() {
            System.out.println("It's me, John!");
        }
    }

    // Accessed via getter method instead.
    private static class Thor
            implements Greeter {
        private static final Thor INSTANCE = new Thor();

        private Thor() {
            // No one else can make Thor!
        }

        public static Thor getInstance() {
            return INSTANCE;
        }

        @Override
        public void greet() {
            System.out.println("Meow!");
        }
    }

    // Utilize enum as singleton.
    private enum People
            implements Greeter {
        NENIR {
            @Override
            public void greet() {
                System.out.println("I am the greatest druid!");
            }
        },
        NURR {
            @Override
            public void greet() {
                System.out.println("Feel my eldritch blasts, fools!");
            }
        },
        LOM {
            @Override
            public void greet() {
                System.out.println("May the spores consume you >:)");
            }
        };
    }
}
