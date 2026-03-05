# SIT305_SIT708---Mobile-Application-Development

<img width="638" height="1228" alt="image" src="https://github.com/user-attachments/assets/fcd12731-7d94-41f2-97f9-5606eb8a05b0" />
Travel Companion App

Travel Companion is an Android app I built for international travel.
It helps with quick conversions you often need on the go — like currency, fuel, liquids, and temperature.

What it can do
1) Conversions by category

Currency: Uses fixed exchange rates (2026 set rates) for USD, AUD, EUR, JPY, GBP

Fuel & distance:

mpg → km/L

gallons → litres

nautical miles → kilometres

Temperature: Celsius ↔ Fahrenheit ↔ Kelvin

2) Validation (based on SIT708 requirements)

I added basic input checks so the app doesn’t crash and the result stays meaningful.

Stops conversion when the input is empty or not a number (shows a Toast message)

Detects when From and To units are the same, so it doesn’t do unnecessary calculation

Blocks negative values for fuel/distance conversions (because it doesn’t make sense in real use)

3) UI

Built using ScrollView + CardView, so it stays readable on different screen sizes

When the user changes the category, the From/To spinners update automatically with the right units

Tech notes

Language: Kotlin

Structure: Activity-based

UI: Material components + Spinner + EditText

Input uses proper inputType (e.g., numberDecimal, numberSigned) depending on the conversion type.

How to use

Pick a Category

Choose From and To

Enter a value

Tap Convert to see the result at the bottom
