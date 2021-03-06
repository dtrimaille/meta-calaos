DESCRIPTION = "libvalhalla is a library written in C. It is a media scanner, that stores various information in an SQLite database and relies on FFmpeg (libavformat and libavutil) and libcurl. It features many Internet grabbers that allows automatic download of covers, lyrics, informations on media files, tags retrival in video and music files and so on."
HOMEPAGE = "http://libvalhalla.geexbox.org/"
SECTION = "libs/multimedia"
LICENSE = "LGPLv2.1"
DEPENDS = "sqlite3 curl ffmpeg"

LIC_FILES_CHKSUM = "file://COPYING;md5=dafc9d229b65b58f253daf44ad9a821a"

SRC_URI[libvalhalla.md5sum] = "5653ef94243eb9127656aaa97442f002"
SRC_URI[libvalhalla.sha256sum] = "1773b3dc13fa8b584ba86e28750f4e8b2104cc3e570d6d866346f0240eb759bb"

PR = "r1"

SRC_URI = "http://libvalhalla.geexbox.org/releases/${P}.tar.bz2;name=${PN}"

inherit autotools pkgconfig

# the configure script is hand-crafted, it rejects some of the usual
# configure arguments
do_configure() {
	${S}/configure \
			--prefix=${prefix} \
			--disable-strip \
		--with-lavf-inc=${STAGING_INCDIR} \
		--with-lavf-lib=${STAGING_LIBDIR} \
		--with-lavc-inc=${STAGING_INCDIR} \
		--with-lavc-lib=${STAGING_LIBDIR} \
		--cross-compile \
		--enable-pic \
                --enable-doc
}

PACKAGES =+ "${PN}-bin"

FILES_${PN}-bin = "${bindir}/*"