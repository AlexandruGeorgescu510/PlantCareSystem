# PlantCareSystem

This project is a simple implementation for a plant care system using a Raspberry Pi with sensors, Firebase, and Android (Java). Repository contains source code for Android app and the Python script for the Raspberry Pi that needs to run continuously.

Features include:
  - user registering and logging in using Firebase default authentication with username and password
  - auto-localising the device based on IP when the script first runs, and displaying the location using Google Maps API in the app (location is approximative and depends on your ISP and the type of connection used)
  - auto watering at 8 / 12 / 24 hours, if the soil is dry
  - light intensity monitoring + complementary lightning (Light Booster)
  - temperature monitoring
  - air humidity monitoring
  - atmospheric pressure monitoring
  - rain sensor (for outside use cases)

Hardware used:
  - Raspberry Pi 4 Model B - 2GB RAM
  - Submersible Waterpump
  - 3V Relay
  - 4 AAA battery additional power source
  - BH1750 Light intensity sensor
  - BME280 Temperature and atmospheric pressure sensor
  - AM2320 Temperature and air humidity sensor
  - Resistive rain sensor
  - Resistive soil humidty sensor
  - LM393 comparator module (for analog sensors)

Implementing the project requires a Firebase NoSql database set and the hardware prototype fully working. In the repo you can find a Fritzing diagram that can help.
