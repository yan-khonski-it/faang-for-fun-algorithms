package com.yk.faang.fun.algorithms.billion_users_apps;

/*
https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=951929261870357&ppid=454615229006519&practice_plan=0

We have N different apps with different user growth rates.
At a given time t, measured in days, the number of users using an app is g^t
(for simplicity we'll allow fractional users), where g is the growth rate for that app.
These apps will all be launched at the same time and no user ever uses more than one of the apps.
We want to know how many total users there are when you add together the number of users from each app.
After how many full days will we have 1 billion total users across the N apps?

Signature
int getBillionUsersDay(float[] growthRates)
Input
1.0 < growthRate < 2.0 for all growth rates
1 <= N <= 1,000
Output
Return the number of full days it will take before we have a total of 1 billion users across all N apps.
Example 1
growthRates = [1.5]
output = 52
Example 2
growthRates = [1.1, 1.2, 1.3]
output = 79
Example 3
growthRates = [1.01, 1.02]
output = 1047
*/
class Main {

  private static final double BILLION_USERS = 1_000_000_000;

  // Add any helper functions you may need here
  double min(float[] array) {
    double res = 2;
    for (int i = 0; i < array.length; i++) {
      if (res > array[i]) {
        res = array[i];
      }
    }

    return res;
  }

  double max(float[] array) {
    double res = 1;
    for (int i = 0; i < array.length; i++) {
      if (res < array[i]) {
        res = array[i];
      }
    }

    return res;
  }

  // log a (b) = ln(b) - ln(a)
  double log(double a, double b) {
    return Math.log(b) / Math.log(a);
  }

  double checkUsers(float[] growthRates, int days) {
    double res = 0;
    for (int i = 0; i < growthRates.length; i++) {
      res = res + Math.pow(growthRates[i], days);
    }

    return res;
  }

  // calculate minimum number of days - use max growth for all apps
  // calculate maximum number of days - use min growth for all apps
  // binary search - check middle number of days - if it is greater or equal to iB users
  int getBillionUsersDay(float[] growthRates) {
    // Write your code here
    double minGrowthRate = min(growthRates);
    double maxGrowthRate = max(growthRates);
    double usersPerApp = BILLION_USERS / growthRates.length;

    // For a single app
    // growthRate ^ numberOfDAys = usersPerApp
    // minGrowRate ^ numberOfDays = totalUsers
    // numberOfDays = log minGrowRate (totalUsers)

    // For all apps
    // sum (growthRate ^ numberOfDays) = BILLION_USERS
    // let's consider growthRates are equal for all apps
    // N * growthRate ^ numberOfDays = BILLION_USERS

    int minDays = (int) log(maxGrowthRate, usersPerApp);
    int maxDays = (int) log(minGrowthRate, usersPerApp);

    while (minDays < maxDays - 1) {
      int middle = (minDays + maxDays) / 2;
      double currentUsers = checkUsers(growthRates, middle);
      if (currentUsers == BILLION_USERS) {
        return middle;
      }

      if (currentUsers < BILLION_USERS) {
        minDays = middle;
      } else {
        maxDays = middle;
      }
    }

    if (checkUsers(growthRates, minDays) >= BILLION_USERS) {
      return minDays;
    } else {
      return maxDays;
    }
  }














  // These are the tests we use to determine if the solution is correct.
  // You can add your own at the bottom.
  int test_case_number = 1;

  void check(int expected, int output) {
    boolean result = (expected == output);
    char rightTick = '\u2713';
    char wrongTick = '\u2717';
    if (result) {
      System.out.println(rightTick + " Test #" + test_case_number);
    }
    else {
      System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
      printInteger(expected);
      System.out.print(" Your output: ");
      printInteger(output);
      System.out.println();
    }
    test_case_number++;
  }

  void printInteger(int n) {
    System.out.print("[" + n + "]");
  }

  public void run() {
    float[] test_1 = {1.1f, 1.2f, 1.3f};
    int expected_1 = 79;
    int output_1 = getBillionUsersDay(test_1);
    check(expected_1, output_1);

    float[] test_2 = {1.01f, 1.02f};
    int expected_2 = 1047;
    int output_2 = getBillionUsersDay(test_2);
    check(expected_2, output_2);


    // Add your own test cases here

  }

  public static void main(String[] args) {
    new Main().run();
  }
}
