# What is ROARbot?

For my final project in IST 261, a course offered Pennsylvania State University, I decided to create ROARbot. You might ask youself, "what exactly is a ROARbot?" Well, let me tell you! ROARbot is a chatbot that helps users of the [Roar supercomputer](https://www.icds.psu.edu/computing-services/system-specifications/). When I was designing ROARbot, I set out to accomplish four tasks:

1. Help users understand how to use common Linux commands.
2. Help users create basic [PBS (Portable Batch System)](https://en.wikipedia.org/wiki/Portable_Batch_System) script.
3. Basic troubleshooting for when things go wrong.
4. Retrieve man page documentation for basic Linux commands.

While these tasks may seem silly to the semi-competent Linux aficionado (personally I love Ubuntu and Garuda Linux), researchers are researchers, not Linux people. They use computers because they want to save trees and have a life outside their work, not because they want to flex on the haters at parties ;-)

# See ROARbot in action!
In order to be graded on how well I built ROARbot, I recorded a YouTube video of ROARbot doing its thing. In the video, I demonstrate ROARbot's basic capabilites. You should be able to view the video below, but if it does not work for you, then please follow the YouTube link that I included below:



https://www.youtube.com/watch?v=fITgYZ2PY-w

# The tools I used

As you can tell by the repository information, ROARbot is implemented in Java. While some plebians might say, "oh that's just the College of IST doing its thing," I will not discount the amazing portability and industrial strength of the Java programming language! Also, Java has some easy to use UI libraries. See the other dependencies that I have included below:

1. Maven
2. SQLite
3. JavaTuples

I will warn you, however, that I did not build ROARbot with the intention of it being portable. If you are interested in the further development of ROARbot, check out the Troubleshooting section of this README.

# License

![Github](https://img.shields.io/github/license/NucciTheBoss/ROARbot)

This repository is licensed under the permisive MIT License. For more information on what this license entails, please feel free to visit https://en.wikipedia.org/wiki/MIT_License.

# Troubleshooting

If you encounter issues while using ROARbot, unfortunately this project is archived. While ROARbot is definitely an awesome project, I do not have the time nor enough support to continue its development. That being said, if you are interested in furthering the development of ROARbot, or even considering adapting the code base to another high-performance computing cluster, then please contact me at jcn23@psu.edu. I am always excited to talk about ROARbot, and I wouldn't mind further developing ROARbot if it could become something more than just a class project!