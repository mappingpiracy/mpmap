cd app/assets 
find . -name '*.js' -type f -exec sh -c \
    'ls ${0}' {} \;
