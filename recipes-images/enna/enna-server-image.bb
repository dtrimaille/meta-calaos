DESCRIPTION = "Calaos server - image"

require ennaos-base-image.bb

IMAGE_INSTALL += " watchdog "
IMAGE_INSTALL += " lighttpd lighttpd-module-fastcgi "
IMAGE_INSTALL += " avahi avahi-daemon avahi-systemd avahi-utils "
IMAGE_INSTALL += " php-cli "
IMAGE_INSTALL += " www-enna "
IMAGE_INSTALL += " alsa-utils-aplay alsa-utils-amixer "

export IMAGE_BASENAME = "enna-server-image"
