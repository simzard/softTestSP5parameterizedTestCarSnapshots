# softTestSP5parameterizedTestCarSnapshots

# Purpose of the original test is to test the Intelligence.recognize() method and give a count of succesful tests of the car snapshots compared with a list of test plates. Dosn't provide any details about which tests that fail.

# Purpose of my parameterized test will also give information about which values fail, so that it could be corrected in future versions of the software.

# I have used parameterized tests to make the test depend on a file with the test values. This is good because the test code doesn't have to change, if the test input data changes

# To include hamcrest: change maven pom.xml by adding xml depency tags. The difference is I get the exact input and output values for the result of the failing tests
