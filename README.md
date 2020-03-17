# App for Firefighter Project
Made by: Megan Chiovaro
Last updated:

## Description

This app collects and saves movement data from an Android device including accelerometer, gyroscope, and magnetometer and saves to a .txt file.

## Grabbing data

(not used anymore)
To grab Logcat (datafile) from a phone:
  1. Connect device to computer via USB
  2. Launch terminal and navigate to SDK platform-tools (typically in '/Users/<user>/Library/Android/sdk/platform-tools')
  3. Run 'adb logcat -d > logcat.txt'

(current method)
To grab from internal storage:
  1. Connect device to computer via USB
  2. Open Android Studio
  3. Navigate to: View -> Tool Windows -> Device File Explorer
  4. Once the explorer appears, select the device from the drop down menu
  5. Navigate to: data/data/com.example.firefighters/files/
  5. Download data files locally and delete from device if desired
