# Dragon Touch Tailscale Boot (experimental)

Android 6.0.1 (API 23) Java boot receiver. Android Gradle Plugin 8.6.1, Gradle 8.7, JDK 17 required. Build in Android Studio (select JDK 17) or with a local Gradle 8.7 installation: `gradle assembleDebug`. Android SDK Platform 35 must be installed. No prebuilt APK is included.

## Before installing

1. Preserve your current authenticated Tailscale state. Do not move or copy the state while `tailscaled` is running. Stop the daemon cleanly first, back up `/data/local/tmp/tailscale-state`, then copy it to `/data/local/tailscale/state`.
2. Copy the ARMv7 `tailscaled` and `tailscale` binaries to `/data/local/tailscale/`.
3. Copy `start.sh` to `/data/local/tailscale/start.sh`; set root ownership and restrictive permissions. Check that the exact XDG/TS_LOGS_DIR environment from your working foreground session is preserved.
4. Test `/system/bin/sh /data/local/tailscale/start.sh` manually via root ADB and check the daemon status BEFORE installing the APK.
5. Install the built debug APK with `adb install -r app/build/outputs/apk/debug/app-debug.apk` and open the app once.
6. Verify `su -c` works for the app. Root ADB does not guarantee SuperSU grants privileges to applications. Check `adb logcat -s DragonTailscaleBoot`.
7. Reboot and verify status. Android 6 firmware may suppress BOOT_COMPLETED or kill services. START_STICKY is not a full process supervisor; this prototype does not guarantee recovery if the child daemon later dies.

## Important

No root permission is requested in the Android manifest because SuperSU handles authorization separately. Avoid `adb shell am broadcast` for BOOT_COMPLETED testing; use a real reboot. Do not expose ADB port 5555 publicly. This source is a prototype, not a tested binary for the Dragon Touch.
