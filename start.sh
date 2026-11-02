#!/system/bin/sh
BASE=/data/local/tailscale
export HOME="$BASE"
export XDG_STATE_HOME="$BASE/state"
export XDG_CACHE_HOME="$BASE/cache"
export TS_LOGS_DIR="$BASE/logs"
mkdir -p "$BASE/state" "$BASE/cache" "$BASE/logs"
# Avoid starting a second instance. Requires an Android-compatible pidof.
if /system/bin/ps | /system/bin/grep '[t]ailscaled' >/dev/null 2>&1; then
    exit 0
fi
"$BASE/tailscaled" --tun=userspace-networking --socket="$BASE/tailscaled.sock" --statedir="$BASE/state" >>"$BASE/daemon.log" 2>&1 </dev/null &
exit 0
