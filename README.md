# WEB

src_main____java__com.estsoft.codit.web__controller
        |                              |_vo
        |                              |_service
        |                              |_repository
        |
        |_X_resources__mybatis__mapper__main
        |            |        |       |_user
        |            |        |       |_recruit
        |            |        |
        |            |        |_configuration
        |            |
        |            |_logback
        |
        |___webapp__assets__css
                  |       |_images
                  |       |_js
                  |
                  |_WEB-INF__views__main
                           |      |_user
                           |      |_recruit
                           |
                           |_dispatcher-servlet.xml
                           |
                           |_web.xml

"`

<<<<<<< HEAD
# git clone <remote repo name> <dir name>

# cd ..
# cd <dir name>

# ls -la 
estsoft@ǻó▒▒1 MINGW64 /c/IdeaProjects/project-codit

# git status -> check branch

# git branch <'branch-name'>

# git checkout <'branch-name'>

# EDITING

# git add <file name>  //   git add .

# git commit -m <'commit message'>

# git checkout master


## THREE WAY MERGE
## git add .
## git commt -m <'master-commit-message'>
## git pull


# git merge <'branch-name'>

###CONFLICT START

# git status  --> check conflict

# git mergetool

# git status  --> check if resolved

# git add .

# git commit <'merge-complete-message'>

###CONFLICT END


# git push origin master

##### git push <remote repo alias> <branch name>

#######


## It will be conflict. ## GOOD

# branch add commit merge

### create new branch and checkout   [branch : master --> branch-test]
# git branch 'branch-test'        +
# git checkout branch-test        = > git checkout -b 'branch-test'

### add and commit
# git add README.md         ( or git add . )
# git commit -m 'readme.md branch test'

### back to master branch
# git checkout master

#"  ++THREE WAY MERGE++
# git add <some file name to be added>
# git commit -m 'commit some master feature'
#
# git pull origin master
#"

# git merge 'branch-test'

# WARNING
# both modified

### push commits
# git push origin master

>>>>>>> branch-test
