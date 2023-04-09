package org.ingomohr.bsarator.service.internal.adapter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

import java.util.Objects;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestSimpleBSAratorTextAdapter {

	private SimpleBSAratorTextAdapter objUT;

	@BeforeEach
	void prep() {
		objUT = new SimpleBSAratorTextAdapter();
	}

	@Test
	void adapt_TextIsEmpty_ReturnsEmptyString() {
		assertThat(objUT.adapt(""), is(emptyString()));
	}

	@ParameterizedTest
	@ValueSource(strings = { "Hello", "hello", "HELLO", "This is not okay", "(&; " })
	void adapt_TextIsNotEmpty_ReturnsCapitalizedStringWithRandomUpperLowerCases(String txt) {
		assertThat(objUT.adapt(txt), isSameLettersWithCapitalFirstLetterAndRandomUpperLowerCasesForTheRest(txt));
	}

	private BaseMatcher<String> isSameLettersWithCapitalFirstLetterAndRandomUpperLowerCasesForTheRest(String string) {
		return new DiagnosingMatcher<String>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("\n- Length: " + string.length());
				description.appendText("\n-First letter is capital");
				description.appendText("\n-At least once char is lower case");
				description.appendText("\n-Text remains the same (ignoring cases) to: " + string);
			}

			@Override
			protected boolean matches(Object item, Description mismatchDescription) {
				String actualString = (String) item;

				if (string.length() != actualString.length()) {
					mismatchDescription.appendText("\n  - Length was: " + actualString.length());
					return false;
				}

				char firstC = actualString.charAt(0);
				if (Character.isAlphabetic(firstC)) {
					if (!Character.isUpperCase(firstC)) {
						mismatchDescription.appendText("\n  - First letter was: " + firstC);
						return false;
					}
				}

				long numberOfLowerCaseChars = numberOfLowerCaseChars(actualString);
				if (numberOfLowerCaseChars < 1) {
					mismatchDescription.appendText("\n  - #LowerCaseChars was: " + numberOfLowerCaseChars);
					return false;
				}

				if (!Objects.equals(actualString.toLowerCase(), string.toLowerCase())) {
					mismatchDescription.appendText("\n  - text was: " + actualString);
					return false;
				}

				return true;
			}

			private long numberOfLowerCaseChars(String actualString) {
				return actualString.chars().filter(c -> !Character.isUpperCase(c)).count();
			}

		};
	}

}
