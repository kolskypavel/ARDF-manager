package com.david.ardfmanager;

public class ControlPoint {

        private int number;
        private int code;
        private boolean obligatory;
        private boolean beacon;

        public ControlPoint(int number, int code, boolean obligatory, boolean beacon) {
                this.number = number;
                this.code = code;
                this.obligatory = obligatory;
                this.beacon = beacon;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
                this.number = number;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
                this.code = code;
        }

        public boolean isObligatory() {
            return obligatory;
        }

        public void setObligatory(boolean obligatory) {
            this.obligatory = obligatory;
        }

        public boolean isBeacon() {
            return beacon;
        }

        public void setBeacon(boolean beacon) {
            this.beacon = beacon;
        }

    }
