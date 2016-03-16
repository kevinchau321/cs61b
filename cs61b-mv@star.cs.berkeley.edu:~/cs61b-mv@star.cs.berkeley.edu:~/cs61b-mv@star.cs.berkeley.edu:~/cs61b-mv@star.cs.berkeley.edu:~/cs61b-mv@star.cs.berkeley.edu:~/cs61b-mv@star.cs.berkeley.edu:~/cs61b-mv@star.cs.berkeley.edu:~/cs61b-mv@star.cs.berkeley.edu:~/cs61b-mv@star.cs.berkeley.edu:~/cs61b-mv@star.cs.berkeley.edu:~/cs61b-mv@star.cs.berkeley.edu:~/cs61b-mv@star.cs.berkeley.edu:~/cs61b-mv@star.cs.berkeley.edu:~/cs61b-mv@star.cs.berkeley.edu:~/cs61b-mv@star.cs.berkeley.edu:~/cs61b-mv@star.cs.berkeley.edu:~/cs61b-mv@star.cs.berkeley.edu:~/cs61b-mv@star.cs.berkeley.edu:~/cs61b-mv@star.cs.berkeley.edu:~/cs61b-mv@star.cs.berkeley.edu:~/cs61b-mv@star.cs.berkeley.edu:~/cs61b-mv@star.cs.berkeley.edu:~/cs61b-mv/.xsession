[[ -z ${MASTER} ]] && export MASTER=${USER%-*}
[[ -z ${MASTERDIR} ]] && export MASTERDIR=$(eval echo ~${MASTER})

[[ -e ${MASTERDIR}/adm/class.xsession ]] && . ${MASTERDIR}/adm/class.xsession
