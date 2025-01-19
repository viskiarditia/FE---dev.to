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

  Scenario: TC-F008
    When Kosongkan pada field Email
    Then Kosongkan pada field password
    And tidak mencentang Remember me?
    Then Klik Log in

  Scenario: TC-F009
    When ketikan email tidak valid data, pada field email
    Then ketikan password invalid data kurang dari enam karakter, pada field password

  Scenario Outline: TC-F010 sampai TC-030
    When ketikan email invalid "<data>", pada field email
    Then user mendapatkan "<Expected Result>" seusai dengan test case
  Examples:
    | data                                                          | Expected Result                                                                                            |
    | Masukkan email valid tetapi menggunakan huruf kapital         | adanya Pop up bahwa email tidak valid                                                                      |
    | Verifikasi Masukkan email valid dengan format                 | Sistem menerima email tanpa pesan error                                                                    |
    | Verifikasi Masukkan Format Email Tidak Valid                  | Pesan error ditampilkan: "Format email tidak valid"                                                        |
    | Field Email tidak valid melebihi 25 karakter                  | adanya Pop up batasan maksimal email tidak bisa input lebih 25 karakter sesuai dengan ketentuan perusahaan |
    | Field Email Kosong                                            | Pesan error ditampilkan: "Field email wajib diisi"                                                         |
    | Masukkan email dengan format valid tetapi tanpa nama pengguna | adanya Pop up bahwa email tidak valid                                                                      |
    | Masukkan email valid tetapi dengan spasi di awal/akhir        | adanya Pop up bahwa email tidak valid                                                                      |
    | Verifikasi field email dengan ~ (tilde)                       | adanya Pop up bahwa email tidak valid                                                                      |
    | (exclamation mark)                                            | adanya Pop up bahwa email tidak valid                                                                      |
    | (hash)                                                        | adanya Pop up bahwa email tidak valid                                                                      |
    | (dollar)                                                      | adanya Pop up bahwa email tidak valid                                                                      |
    | (percent)                                                     | adanya Pop up bahwa email tidak valid                                                                      |
    | (caret)                                                       | adanya Pop up bahwa email tidak valid                                                                      |
    | (ampersand)                                                   | adanya Pop up bahwa email tidak valid                                                                      |
    | (asterisk)                                                    | adanya Pop up bahwa email tidak valid                                                                      |
    | (angle brackets)                                              | adanya Pop up bahwa email tidak valid                                                                      |
    | (parentheses)                                                 | adanya Pop up bahwa email tidak valid                                                                      |
    | (underscore)                                                  | Email dapat di input                                                                                       |
    | (plus)                                                        | adanya Pop up bahwa email tidak valid                                                                      |
    | (equals)                                                      | adanya Pop up bahwa email tidak valid                                                                      |
    | (backslash)                                                   | adanya Pop up bahwa email tidak valid                                                                      |
    | (vertical bar)                                                | adanya Pop up bahwa email tidak valid                                                                      |
    | angka                                                         | Email dapat di input                                                                                       |


  Scenario Outline: TC-F031
    When ketikan email invalid "<data>", pada field email untuk case vertical bar
    Then user mendapatkan "<Expected Result>" seusai dengan test case vertical bar
    Examples:
      | data         | Expected Result                       |
      | vertical bar | adanya Pop up bahwa email tidak valid |

  Scenario Outline: TC-F032
    When ketikan email invalid angka "<data>", pada field email untuk case angka
    Examples:
      | data                        |
      | testingquality123@gmail.com |

  Scenario: TC-F033
    When Masukkan email valid data, pada field Email
    Then Kosongkan pada field password
    And Klik Log in

  Scenario: TC-F035
  When Klik teks link Forgot Password?

  Scenario: TC-F036
    When Klik teks link Forgot Password?
    Then Ketika Email yang valid [data] pada field Email
    And Klik button Send Password Reset Link

  Scenario Outline: TC-F037 sampai TC-F038
    When Klik teks link Forgot Password?
    Then Ketika Email yang invalid "<data>" pada field Email
    And Klik button Send Password Reset Link
  Examples:
    | data                          |
    | testinggmailcom               |
    | testingngasalbanget@gmail.com |

  Scenario: TC-F039
     When Klik link Privacy Policy

  Scenario: TC-F040
      When Klik link Terms of Use

  Scenario: TC-F041
      When Klik link Code of Conduct

