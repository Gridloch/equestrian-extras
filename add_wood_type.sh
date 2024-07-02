#!/bin/bash
# Run in strict mode and run a finish function before exiting
set -euo pipefail
trap 'finish $?' EXIT


# What's my name?
declare SCRIPT_NAME
declare SCRIPT_DIR
SCRIPT_NAME=$(basename -- "$0" .sh)
SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd -P)
readonly SCRIPT_NAME
readonly SCRIPT_DIR


# Grab common functions
# shellcheck source=./lib.sh
# shellcheck disable=SC1091
source "${SCRIPT_DIR}/lib.sh"

# Finish Function
finish() {
  log_info "${SCRIPT_NAME}" "*** SCRIPT FINISH ***"
}


# Parse Parameters
parse_params() {
  # default values of variables set from params
  dryrun=0
  help=0
  new_wood=""
  base_wood="birch"
  mod_path=""
  mod_name=""

  while :; do
    case "${1-}" in
      -x | --verbose) set -x ;;
      -d | --dryrun) dryrun=1 ;;
      -h | --help) help=1 ;;
      -w | --new-wood | --wood)
        new_wood="${2-}"
        shift
        ;;
      -b | --base-wood)
        base_wood="${2-}"
        shift
        ;;
      -p | --mod-path | --path)
        mod_path="${2-}"
        shift
        ;;
      -n | --mod-name | --name)
        mod_name="${2-}"
        shift
        ;;
      -?*) die "${SCRIPT_NAME}" "Unknown option: $1" 1 ;;
      *) break ;;
    esac
    shift
  done


  if [[ "${help}" == 1 ]]; then
    log_info "${SCRIPT_NAME}" "
Usage: ${SCRIPT_NAME} [OPTION]
Generates resource files for new wood types.

Options: 
  -b, --base-wood    |  name of the wood type to use as a base for generating files for the new
                     |     wood type (birch by default)
  -d, --dryrun       |  run command without actually making changes to see an example log of what
                     |     will happen
  -h, --help         |  display this help and exit
  -n, --mod-name     |  REQUIRED - the name of the mod (used to find the correct assets folder)
      --name         |
  -p, --mod-path     |  REQUIRED - path to the directory containing the mod
      --path         |
  -w, --new-wood     |  REQUIRED - name of the wood type to add
      --wood         |
  -x, --verbose      |  prints all executed commands to the terminal for debugging"
    exit "0"
  fi

  if [[ "${new_wood}" == "" ]]; then
    die "${SCRIPT_NAME}" "new-wood must be specified (-w | --new-wood | --wood)" 1
  fi

  if [[ "${mod_path}" == "" ]]; then
    die "${SCRIPT_NAME}" "mod-path must be specified (-p | --mod-path | --path)" 1
  fi

  if [[ "${mod_name}" == "" ]]; then
    die "${SCRIPT_NAME}" "mod-name must be specified (-n | --mod-name | --name)" 1
  fi

  

  return 0
}


# Find Files to Copy
find_files() {
  local dir_path="${1-}"
  if [ ! -d "$dir_path" ] ; then
    log_info "${SCRIPT_NAME}" "Invalid directory path. Please check the input."
  else
    local dir_contents
    dir_contents="$(find "$dir_path" -name "${base_wood}*" -type f -print)"
    for f in $dir_contents ; do
      files+=("$(realpath "$f")")
    done
  fi
}


# Main Function
main() {
  log_info "${SCRIPT_NAME}" "*** SCRIPT START ***"
  parse_params "$@"

  # Do Stuff Here
  files=()

  # If you want to avoid doing something during a "dryrun" (i.e. for testing), use the following
  if [[ "${dryrun}" -eq 0 ]]; then
    log_info "${SCRIPT_NAME}" "Finding ${base_wood} block asset files to copy in ${mod_path}/src/main/resources/assets/${mod_name}/blockstates/ and ${mod_path}/src/main/resources/assets/${mod_name}/models/"
    find_files "${mod_path}/src/main/resources/assets/${mod_name}/blockstates/"
    find_files "${mod_path}/src/main/resources/assets/${mod_name}/models/"
    log_info "${SCRIPT_NAME}" "Found ${#files[@]} ${base_wood} block asset files"

    for f in "${files[@]}" ; do
      local new_path="${f/$base_wood/$new_wood}"
      log_info "${SCRIPT_NAME}" "Generating $(awk -F'/' '{print $(NF-1)}' <<< "$f")/$(basename "$new_path")"
      cp "$f" "$new_path"
      sed -i -e "s/$base_wood/$new_wood/g" "$new_path"
    done

    log_info "${SCRIPT_NAME}" "
Blockstate, item model and block model '$new_wood' files should now be generated from '$base_wood' files.
The following still needs to be done:
 - Register block / item
 - Add missing textures
 - Add to lang
 - Update tags
 - Add loot table
 - Add recipes"


  else
    log_warn "${SCRIPT_NAME}" "Log only - files will not actually be generated (dryrun)"
    log_info "${SCRIPT_NAME}" "Finding ${base_wood} block asset files to copy in ${mod_path}/src/main/resources/assets/${mod_name}/blockstates/ and ${mod_path}/src/main/resources/assets/${mod_name}/models/"
    find_files "${mod_path}/src/main/resources/assets/${mod_name}/blockstates/"
    find_files "${mod_path}/src/main/resources/assets/${mod_name}/models/"
    log_info "${SCRIPT_NAME}" "Found ${#files[@]} ${base_wood} block asset files"

    for f in "${files[@]}" ; do
      local new_path="${f/$base_wood/$new_wood}"
      log_info "${SCRIPT_NAME}" "Non-dryrun would generate $(awk -F'/' '{print $(NF-1)}' <<< "$f")/$(basename "$new_path")"
      # cp "$f" "$new_path"
      # sed -i -e "s/$base_wood/$new_wood/g" "$new_path"
    done

    log_info "${SCRIPT_NAME}" "
Blockstate, item model and block model '$new_wood' files would have been generated from '$base_wood' files.
The following would still need to be done:
 - Register block / item
 - Add to lang
 - Update tags
 - Add loot table
 - Add recipes"

  fi
}


# Script 'starts' here
main "$@"