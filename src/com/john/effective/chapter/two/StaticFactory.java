package com.john.effective.chapter.two;

import java.util.Set;

class Program {
    public static void main(String[] args) {
        final StaticFactory james = StaticFactory.person("James");
        final StaticFactory car = StaticFactory.car();
        final StaticFactory motorbike = StaticFactory.motorbike();

        Set.of(james, car, motorbike).forEach(StaticFactory::doSomething);
    }
}

interface StaticFactory {
    void doSomething();

    /**
     * @param name The name of the person.
     * @return A new {@link Person} object with a set name.
     */
    static StaticFactory person(final String name) {
        return new Person(name);
    }

    /**
     * @return A new {@link Vehicle} of type {@link Vehicle.VehicleType .CAR}
     */
    static StaticFactory car() {
        return new Vehicle(Vehicle.VehicleType.CAR);
    }

    /**
     * @return A new {@link Vehicle} of type {@link Vehicle.VehicleType .MOTORBIKE}
     */
    static StaticFactory motorbike() {
        return new Vehicle(Vehicle.VehicleType.MOTORBIKE);
    }

    class Person
            implements StaticFactory {
        private final String name;

        private Person(String name) {
            this.name = name;
        }

        @Override
        public void doSomething() {
            System.out.printf("I am a person names %s and I do stuff.%n", this.name);
        }
    }

    class Vehicle
            implements StaticFactory {
        private final VehicleType type;

        private Vehicle(VehicleType type) {
            this.type = type;
        }

        @Override
        public void doSomething() {
            System.out.printf("I am a %s and I go places.%n", type.name());
        }

        private enum VehicleType {
            CAR,
            MOTORBIKE
        }
    }
}