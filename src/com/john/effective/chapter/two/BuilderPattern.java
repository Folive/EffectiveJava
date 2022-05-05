package com.john.effective.chapter.two;

import java.math.BigInteger;
import java.util.Set;

public class BuilderPattern {
    public static void main(String[] args) {
        final Organism cat = new Organism.Builder("Cat")
                .setActuallyExists(true)
                .setHasHair(true)
                .setNumberOfLegs(4)
                .setNumberOfHeads(1)
                .setNumberOfEyes(2)
                .setNumberOfNoses(1)
                .build();
        final Organism fish = new Organism.Builder("Fish")
                .setNumberOfScales(1000)
                .setNumberOfHeads(1)
                .setNumberOfEyes(2)
                .build();

        Set.of(cat, fish).forEach(System.out::println);
    }

    static abstract class Thing {
        protected final boolean actuallyExists;

        Thing(final boolean actuallyExists) {
            this.actuallyExists = actuallyExists;
        }

        static abstract class Builder<T extends Builder<T>> {
            protected boolean actuallyExists;

            public T setActuallyExists(final boolean actuallyExists) {
                 this.actuallyExists = actuallyExists;
                 return self();
            }

            public abstract Thing build();

            protected abstract T self();
        }
    }

    static class Organism
            extends Thing {
        private final String speciesName;
        private final int numberOfLegs;
        private final int numberOfWings;
        private final int numberOfHeads;
        private final int numberOfEyes;
        private final int numberOfNoses;
        private final int numberOfEars;
        private final boolean hasHair;
        private final BigInteger massMicrograms;
        private final int numberOfTeeth;
        private final int numberOfScales;

        private Organism(final Builder builder) {
            super(builder.actuallyExists);

            this.speciesName = builder.speciesName;
            this.numberOfLegs = builder.numberOfLegs;
            this.numberOfWings = builder.numberOfWings;
            this.numberOfHeads = builder.numberOfHeads;
            this.numberOfEyes = builder.numberOfEyes;
            this.numberOfNoses = builder.numberOfNoses;
            this.numberOfEars = builder.numberOfEars;
            this.hasHair = builder.hasHair;
            this.massMicrograms = builder.massMicrograms;
            this.numberOfTeeth = builder.numberOfTeeth;
            this.numberOfScales = builder.numberOfScales;
        }

        @Override
        public String toString() {
            return "Organism{" +
                    "actuallyExists=" + actuallyExists +
                    ", speciesName='" + speciesName + '\'' +
                    ", numberOfLegs=" + numberOfLegs +
                    ", numberOfWings=" + numberOfWings +
                    ", numberOfHeads=" + numberOfHeads +
                    ", numberOfEyes=" + numberOfEyes +
                    ", numberOfNoses=" + numberOfNoses +
                    ", numberOfEars=" + numberOfEars +
                    ", hasHair=" + hasHair +
                    ", massMicrograms=" + massMicrograms +
                    ", numberOfTeeth=" + numberOfTeeth +
                    ", numberOfScales=" + numberOfScales +
                    '}';
        }

        public static class Builder
                extends Thing.Builder<Builder> {
            private String speciesName;
            private int numberOfLegs;
            private int numberOfWings;
            private int numberOfHeads;
            private int numberOfEyes;
            private int numberOfNoses;
            private int numberOfEars;
            private boolean hasHair;
            private BigInteger massMicrograms;
            private int numberOfTeeth;
            private int numberOfScales;

            public Builder(final String speciesName) {
                setSpeciesName(speciesName);
            }

            public Builder setSpeciesName(String speciesName) {
                if (speciesName.isEmpty()) {
                    throw new IllegalArgumentException("A species must have a name!");
                }

                this.speciesName = speciesName;
                return this;
            }

            public Builder setNumberOfLegs(int numberOfLegs) {
                this.numberOfLegs = numberOfLegs;
                return this;
            }

            public Builder setNumberOfWings(int numberOfWings) {
                this.numberOfWings = numberOfWings;
                return this;
            }

            public Builder setNumberOfHeads(int numberOfHeads) {
                this.numberOfHeads = numberOfHeads;
                return this;
            }

            public Builder setNumberOfEyes(int numberOfEyes) {
                this.numberOfEyes = numberOfEyes;
                return this;
            }

            public Builder setNumberOfNoses(int numberOfNoses) {
                this.numberOfNoses = numberOfNoses;
                return this;
            }

            public Builder setNumberOfEars(int numberOfEars) {
                this.numberOfEars = numberOfEars;
                return this;
            }

            public Builder setHasHair(boolean hasHair) {
                this.hasHair = hasHair;
                return this;
            }

            public Builder setMassMicrograms(BigInteger massMicrograms) {
                this.massMicrograms = massMicrograms;
                return this;
            }

            public Builder setNumberOfTeeth(int numberOfTeeth) {
                this.numberOfTeeth = numberOfTeeth;
                return this;
            }

            public Builder setNumberOfScales(int numberOfScales) {
                this.numberOfScales = numberOfScales;
                return this;
            }

            public BuilderPattern.Organism build() {
                if (this.numberOfEyes > 0 && this.numberOfHeads < 0) {
                    throw new IllegalArgumentException("If it has eyes, it needs a head...I think?");
                }

                return new BuilderPattern.Organism(this);
            }

            @Override
            protected Builder self() {
                return this;
            }
        }
    }
}
