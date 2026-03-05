# SIT305_SIT708---Mobile-Application-Development

<img width="638" height="1228" alt="image" src="https://github.com/user-attachments/assets/fcd12731-7d94-41f2-97f9-5606eb8a05b0" />
Travel Companion App

Travel Companion App

Travel Companion is a simple Android app made to help travellers with common conversions.
When travelling between countries, you often need to convert things like currency, fuel usage, or temperature. This app brings those conversions together in one place so they are quick and easy to use.

Features
Multiple Conversion Types

The app supports several categories of conversions.

Currency – converts between USD, AUD, EUR, JPY, and GBP using fixed exchange rates.

Fuel and Distance – includes conversions such as mpg to km/L, gallons to litres, and nautical miles to kilometres.

Temperature – supports conversions between Celsius, Fahrenheit, and Kelvin.

Input Validation

To make the app more stable and user-friendly, some basic checks are included.

Prevents crashes when the input field is empty or contains invalid values.

Shows a Toast message if the user tries to convert without entering a valid number.

Detects when the From and To units are the same and avoids unnecessary calculations.

Negative values are restricted for fuel and distance conversions because they do not make sense in real-world scenarios.

User Interface

The interface is designed to stay simple and easy to understand.

Uses ScrollView and CardView so the layout works well on different screen sizes.

The From and To dropdown menus update automatically when a new conversion category is selected.

Results appear clearly at the bottom after pressing the Convert button.

Technical Details

Language: Kotlin

Architecture: Activity-based structure

UI Components: Material components, Spinner, EditText, Button, and TextView

EditText fields use appropriate input types such as numberDecimal or numberSigned depending on the conversion type.

How to Use

Select a conversion category

Choose the From unit

Choose the To unit

Enter the value you want to convert

Tap Convert to see the result
