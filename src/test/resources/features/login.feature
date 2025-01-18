@Login
Feature: Login
    As a user I want to login website sauce demo

  Background: membuka halaman dev.to
    Given Halaman DEV Community terbuka di browser


  Scenario Outline: TC-F001, TC-F002, TC-F003, TC-F004, TC-F005, TC-F006
    When Klik tombol Continue with "<Platform>"
    Then Halaman Masuk ke dengan "<getText>" dapat ditampilkan dengan form yang sesuai
  Examples:
    | Platform    | getText                                              |
    | Apple       | In setting up Sign in with Apple                     |
    | Facebook    | Log in to Facebook                                   |
    | Forem       | Authorize DEV Community to access your Forem account |
    | GitHub      | Sign in to GitHub                                    |
    | Google      | Sign in                                              |
    | Twitter (X) | Authorize The DEV Community to access your account?  |


  Scenario: TC-F007
      When ketikan email invalid data, pada field email
      Then ketikan password invalid data, pada field password
      And tidak mencentang Remember me?
      Then Klik Log in




