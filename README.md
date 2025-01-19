<h1>🚀 DEV.to Frontend Automation Testing</h1>
    
<h2>🎯 Overview</h2>
    <p>Welcome to our automated testing suite for DEV.to's frontend login functionality! Built with Java Selenium and BDD, this project aims to ensure robust testing of the DEV.to platform - where developers connect, share knowledge, and grow together! ✨</p>

<h2>🛠️ Technical Stack</h2>
    <ul>
        <li>☕ Java (JDK 11+)</li>
        <li>🌐 Selenium WebDriver</li>
        <li>🥒 Cucumber (BDD)</li>
        <li>🏗️ Maven</li>
        <li>📊 Cucumber HTML Reports</li>
    </ul>

<h2>📁 Project Structure</h2>
    <pre>
📦 src/
 ┣ 📂 main/java/config/    # Configuration files
 ┗ 📂 test/
    ┣ 📂 java/
    ┃  ┣ 📂 pages/        # Login page objects
    ┃  ┣ 📂 steps/        # Step definitions
    ┃  ┗ 📂 utils/        # Helper classes
    ┗ 📂 resources/
       ┗ 📂 features/     # Login feature files</pre>

<h2>⚡ Quick Setup</h2>
    <ol>
        <li>📥 Clone repository:
            <pre>git clone https://github.com/your-username/devto-frontend-testing.git</pre>
        </li>
        <li>🔧 Install dependencies:
            <pre>mvn clean install</pre>
        </li>
        <li>⚙️ Configure properties:
            <pre>
browser=chrome
baseUrl=https://dev.to
implicitWait=10</pre>
        </li>
    </ol>

<h2>▶️ Running Tests</h2>
    <p>🎮 Execute all tests:</p>
    <pre>mvn clean test</pre>
    <p>🎯 Run specific feature:</p>
    <pre>mvn test -Dcucumber.options="--tags @login"</pre>

<h2>📝 Test Example</h2>
    <pre>
Feature: DEV.to Login Functionality ✨
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

<h2>💡 Best Practices</h2>
    <ul>
        <li>🏗️ Follow Page Object Model pattern</li>
        <li>🔄 Keep test scenarios independent</li>
        <li>📊 Maintain test data separately</li>
        <li>📚 Add appropriate documentation</li>
    </ul>

<h2>🤝 Contributing</h2>
    <ol>
        <li>🍴 Fork the repository</li>
        <li>🌱 Create feature branch</li>
        <li>💾 Commit changes</li>
        <li>🚀 Push to branch</li>
        <li>📬 Open Pull Request</li>
    </ol>
    
<h2>📄 License</h2>
    <p>🔓 This project is licensed under the MIT License. Feel free to use and modify!</p>

<footer style="text-align: center; margin-top: 40px; color: #586069; border-top: 1px solid #e1e4e8; padding-top: 20px;">
        <p>🌟 Happy Testing! Let's make DEV.to even better together! 🚀</p>
    </footer>
