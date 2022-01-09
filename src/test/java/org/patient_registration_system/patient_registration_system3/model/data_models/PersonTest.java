package org.patient_registration_system.patient_registration_system3.model.data_models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.patient_registration_system.patient_registration_system3.model.exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person ps = null;

    @BeforeEach
    void setUp() {
        ps = new Person();
    }

    @Nested
    class setIDTest{
        @Test
        void setIdNullTest(){
            assertThrows(NullPointerException.class, ()->ps.setId(null));
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "1111111111A", "A1111111111", "XD", "AA11AA11AA1"})
        void setIdInvalidFormatTest(String input){assertThrows(InvalidPESELFormatException.class, ()->ps.setId(input));}

        @ParameterizedTest
        @ValueSource(strings = {
                "97011454235",
                "87082784741",
                "05210423854",
                "79082535337",
                "02210229514",
                "92061056937",
                "03270842699",
                "72100531512",
                "85081799185",
                "51090382581"
        })
        void setIdInvalidChecksumTest(String input){assertThrows(InvalidPESELChecksumException.class, ()->ps.setId(input));}

        @ParameterizedTest
        @ValueSource(strings = {
                "97011454236",
                "87082784745",
                "05210423856",
                "79082535339",
                "02210229512",
                "92061056933",
                "03270842691",
                "72100531517",
                "85081799184",
                "51090382589"})
        void setIdValidTest(String input){assertDoesNotThrow(()->ps.setId(input));}
    }

    @Nested
    class SetNameTest{
        @Test
        void setNameNullTest(){
            assertThrows(NullPointerException.class, ()->ps.setName(null));
        }

        @ParameterizedTest
        @ValueSource(strings = {"aaa", "A", "A1", "1A", "Aa1", "name"})
        void setNameInvalidValues(String input){
            assertThrows(InvalidNameFormatException.class, ()-> ps.setName(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {"Aa", "Name"})
        void setNameValidValues(String input){
            assertDoesNotThrow(()-> ps.setName(input));
        }
    }

    @Nested
    class SetSurnameTest{
        @Test
        void setSurnameNullTest(){
            assertThrows(NullPointerException.class, ()->ps.setSurname(null));
        }

        @ParameterizedTest
        @ValueSource(strings = {"aaa", "A", "A1", "1A", "Aa1", "name"})
        void setSurnameInvalidValues(String input){
            assertThrows(InvalidSurnameFormatException.class, ()-> ps.setSurname(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {"Aa", "Name"})
        void setSurnameValidValues(String input){
            assertDoesNotThrow(()-> ps.setSurname(input));
        }
    }

    @Nested
    class SetMiddleName {
        @Test
        void setMiddleNameNullTest(){
            assertThrows(NullPointerException.class, ()->ps.setMiddleName(null));
        }

        @ParameterizedTest
        @ValueSource(strings = {"aaa", "A", "A1", "1A", "Aa1", "name"})
        void setMiddleNameInvalidValues(String input){
            assertThrows(InvalidMiddleNameFormatException.class, ()-> ps.setMiddleName(input));
        }

        @ParameterizedTest
        @ValueSource(strings = {"Aa", "Name", ""})
        void setMiddleNameValidValues(String input){
            assertDoesNotThrow(()-> ps.setMiddleName(input));
        }
    }

    @Nested
    class SetEmailTest{
        @Test
        void setEmailInvalidNull(){
            assertThrows(NullPointerException.class, ()->ps.setEmail(null));
        }

        @ParameterizedTest
        @ValueSource(strings = {"invalid", "invalid@", "@","@invalid", "@invalid.com"})
        void setEmailInvalidFormat(String email){
            assertThrows(InvalidEmailFormatException.class, ()->ps.setEmail(email));
        }

        @ParameterizedTest
        @ValueSource(strings = {"valid@valid.com", "valid123@valid.com", "123valid@valid.com", ""})
        void setEmailValid(String email){
            assertDoesNotThrow(()->ps.setEmail(email));
        }
    }
}