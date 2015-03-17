cd app/assets 
find . -name '*.js' -type f -exec sh -c \
    'ls ${0}' {} \;

find . -name '*.css' -type f -exec sh -c \
    'ls ${0}' {} \;

