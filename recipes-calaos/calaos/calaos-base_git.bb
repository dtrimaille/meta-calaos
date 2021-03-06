DESCRIPTION = "Calaos Graphical User Insterface"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "libsigc++-2.0 owfs log4cpp jansson lua5.1 elementary"

PE = "1"
PV = "2.0.0+git${SRCPV}"

SRCREV = "d8b4931f80314e7186ecaf5d6b4944e0320bca6a"
SECTION = "x11/multimedia"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/calaos/calaos_base.git;protocol=http;branch=master \
           file://calaos-server.service \
           file://calaos-home.service \
           file://calaos_home.sh"

inherit autotools gettext systemd update-alternatives

do_configure_prepend() {
    autopoint || touch config.rpath
}

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/calaos-server.service ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/calaos-home.service ${D}${systemd_unitdir}/system
	install -m 0755 ${WORKDIR}/calaos_home.sh ${D}${bindir}
}

FILES_${PN}-dbg += "${srcdir}/* ${bindir}/.debug ${libdir}/calaos/widgets/*/.debug"
FILES_${PN}-dev += "${libdir}/calaos/widgets/*/*.la"

PACKAGES = "calaos-server calaos-home calaos-base calaos-base-dbg calaos-base-dev"

#Clock widget
PACKAGES += "calaos-home-widget-clock"
FILES_calaos-home-widget-clock = "${libdir}/calaos/widgets/clock/module.so"
FILES_calaos-home-widget-clock += "${datadir}/calaos/widgets/clock/*.edj"

#Note widget
PACKAGES += "calaos-home-widget-note"
FILES_calaos-home-widget-note = "${libdir}/calaos/widgets/note/module.so"
FILES_calaos-home-widget-note += "${datadir}/calaos/widgets/note/*.edj"

FILES_calaos-server = "${bindir}/calaos_server \
                    ${systemd_unitdir}/system/calaos-server.service \
                    ${bindir}/calaos_config \
                    ${bindir}/wago_test \
                    ${bindir}/calaos_mail \
                    "

FILES_calaos-home = "${bindir}/calaos_home \
                ${bindir}/calaos_thumb \
	            ${datadir}/calaos/default.edj \
	            ${datadir}/locale/* \
	            ${systemd_unitdir}/system/calaos-home.service \
	            ${bindir}/calaos_home.sh"

RRECOMMENDS_${PN} += "calaos-home-widget-clock \
                      calaos-home-widget-note \
                      "

SYSTEMD_SERVICE = "calaos-server.service calaos-home.service"

