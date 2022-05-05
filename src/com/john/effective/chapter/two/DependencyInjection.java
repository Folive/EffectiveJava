package com.john.effective.chapter.two;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Supplier;

public class DependencyInjection {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        EnumSet.allOf(Ammunition.class).forEach(DependencyInjection::shoot);

        System.out.println("Oooo, what is this random ammo on the ground...");
        shoot(DependencyInjection::getRandomAmmo);
    }

    private static void shoot(final Supplier<Ammunition> ammo) {
        // It'l do... not a great example but I'm tired -_-it
        shoot(ammo.get());
    }

    private static void shoot(final Shootable ammo) {
        final int numberOfShots = Integer.max(3, RANDOM.nextInt(9));
        final Gun gun = new Gun(ammo);
        for (int shots = 0; shots < numberOfShots; shots++) {
            gun.shoot();
        }
    }

    private static Ammunition getRandomAmmo() {
        return EnumSet.allOf(Ammunition.class).stream()
                .filter(a -> RANDOM.nextBoolean())
                .findFirst()
                .orElse(Ammunition.DUD);
    }

    private interface Shootable {
        void collides();
    }

    private static class Gun {
        private final Random random;
        private final Shootable ammo;

        public Gun(final Supplier<Shootable> ammo) {
            this(ammo.get());
        }

        public Gun(final Shootable ammo) {
            this.ammo = ammo;
            this.random = new Random();

            System.out.printf("%s ammo, locked and loaded...%n", ammo);
        }

        public void shoot() {
            if (this.random.nextBoolean()) {
                System.out.println("Darn you missed.");
            } else {
                this.ammo.collides();
            }
        }
    }

    private enum Ammunition implements Shootable {
        TYPICAL {
            @Override
            public void collides() {
                System.out.println("*PEW PING*!");
            }
        },
        EXPLOSIVE {
            @Override
            public void collides() {
                System.out.println("BOOOM POP BOOM!");
            }
        },
        DUD {
            @Override
            public void collides() {
                System.out.println("tick...");
            }
        };
    }
}
